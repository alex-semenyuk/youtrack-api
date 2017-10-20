/*
 * Copyright 2017 George Aristy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.llorllale.youtrack.api;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.llorllale.youtrack.api.session.Session;
import org.llorllale.youtrack.api.session.UnauthorizedException;
import org.llorllale.youtrack.api.util.HttpEntityAsJaxb;
import org.llorllale.youtrack.api.util.HttpRequestWithSession;
import org.llorllale.youtrack.api.util.UncheckedUriBuilder;
import org.llorllale.youtrack.api.util.response.HttpResponseAsResponse;

import java.io.IOException;
import java.util.Optional;

/**
 * Default implementation of {@link UsersOfIssue}.
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.5.0
 */
class DefaultUsersOfIssue implements UsersOfIssue {
  private final Session session;
  private final Issue issue;
  private final org.llorllale.youtrack.api.jaxb.Issue jaxbIssue;
  private final Field field;
  private final HttpClient httpClient;

  /**
   * Primary ctor.
   * @param session the user's {@link Session}
   * @param issue the parent {@link Issue}
   * @param jaxbIssue the jaxb instance of an Issue
   * @param httpClient the {@link HttpClient} to use
   * @since 0.5.0
   */
  DefaultUsersOfIssue(
      Session session,
      Issue issue,
      org.llorllale.youtrack.api.jaxb.Issue jaxbIssue,
      HttpClient httpClient
  ) {
    this.session = session;
    this.issue = issue;
    this.jaxbIssue = jaxbIssue;
    this.field = new BasicField("Assignee", issue.project());
    this.httpClient = httpClient;
  }

  /**
   * Uses the {@link HttpClients#createDefault() default} http client.
   * @param session the user's {@link Session}
   * @param issue the parent {@link Issue}
   * @param jaxbIssue the jaxb instance of an Issue
   * @since 0.5.0
   */
  DefaultUsersOfIssue(
      Session session,
      Issue issue,
      org.llorllale.youtrack.api.jaxb.Issue jaxbIssue
  ) {
    this(session, issue, jaxbIssue, HttpClients.createDefault());
  }

  @Override
  public User creator() throws IOException, UnauthorizedException {
    return new XmlUser(
        getJaxbUser(
            jaxbIssue.getField()
                .stream()
                .filter(f -> "reporterName".equals(f.getName()))
                .map(f -> f.getValue().getValue())
                .findFirst()
                .get()  //expected
        )
    );
  }

  @Override
  public Optional<User> updater() throws IOException, UnauthorizedException {
    final Optional<String> updaterLoginName = jaxbIssue.getField()
        .stream()
        .filter(f -> "updaterName".equals(f.getName()))
        .map(f -> f.getValue().getValue())
        .findFirst();

    final Optional<User> updater;

    if (updaterLoginName.isPresent()) {
      updater = Optional.of(new XmlUser(getJaxbUser(updaterLoginName.get())));
    } else {
      updater = Optional.empty();
    }

    return updater;
  }

  @Override
  public Optional<User> assignee() throws IOException, UnauthorizedException {
    final Optional<String> assigneeLoginName = jaxbIssue.getField()
        .stream()
        .filter(f -> field.name().equals(f.getName()))
        .map(f -> f.getValue().getValue())
        .findFirst();

    final Optional<User> assignee;

    if (assigneeLoginName.isPresent()) {
      assignee = Optional.of(new XmlUser(getJaxbUser(assigneeLoginName.get())));
    } else {
      assignee = Optional.empty();
    }

    return assignee;
  }

  @Override
  public UsersOfIssue assignTo(User user) throws IOException, UnauthorizedException {
    return this.issue().update().field(
        this.field,
        new BasicFieldValue(user.loginName(), this.field)
    ).users();
  }

  private org.llorllale.youtrack.api.jaxb.User getJaxbUser(String loginName) 
      throws IOException, UnauthorizedException {
    return new HttpEntityAsJaxb<>(org.llorllale.youtrack.api.jaxb.User.class)
        .apply(
            new HttpResponseAsResponse(
                httpClient.execute(
                    new HttpRequestWithSession(
                        session, 
                        new HttpGet(
                            new UncheckedUriBuilder(
                                session.baseUrl()
                                    .toString()
                                    .concat("/user/")
                                    .concat(loginName)
                            ).build()
                        )
                    )
                )
            ).asHttpResponse().getEntity()
        );
  }

  @Override
  public Issue issue() {
    return issue;
  }
}
