/*
 * Copyright 2017 George Aristy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.llorllale.youtrack.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.llorllale.youtrack.api.Issues.IssueSpec;
import org.llorllale.youtrack.api.mock.MockProject;
import org.llorllale.youtrack.api.session.PermanentTokenLogin;
import org.llorllale.youtrack.api.session.Session;

/**
 * Integration tests for {@link DefaultIssues}.
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.4.0
 */
public class DefaultIssuesIT {
  private static IntegrationTestsConfig config;
  private static Session session;

  @BeforeClass
  public static void setup() throws Exception {
    config = new IntegrationTestsConfig();
    session = new PermanentTokenLogin(
        config.youtrackUrl(), 
        config.youtrackUserToken()
    ).login();
  }

  @Test
  public void testAll() throws Exception {
    final Issue issue = new DefaultIssues(
        project(),
        session
    ).create(new IssueSpec(DefaultIssuesIT.class.getSimpleName().concat("testAll"), "description"));

    assertTrue(
        new DefaultIssues(
            project(),
            session
        ).all()
            .stream()
            .anyMatch(i -> i.id().equals(issue.id()))
    );
  }

  @Test
  public void testGet() throws Exception {
    final Issue issue = new DefaultIssues(
        project(),
        session
    ).create(new IssueSpec(DefaultIssuesIT.class.getSimpleName().concat("testGet"), "description"));


    assertTrue(
        new DefaultIssues(
            project(),
            session
        ).get(issue.id()).isPresent()
    );
  }

  @Test
  public void testCreate() throws Exception {
    assertNotNull(
        new DefaultIssues(
            project(),
            session
        ).create(new IssueSpec(DefaultIssuesIT.class.getSimpleName().concat("testCreate"), "description"))
    );
  }

  private final Project project() {
    return new MockProject(
        config.youtrackTestProjectId(), 
        "", 
        ""
    );
  }
}