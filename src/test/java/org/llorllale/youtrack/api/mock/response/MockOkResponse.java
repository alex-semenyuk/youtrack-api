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

package org.llorllale.youtrack.api.mock.response;

import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.params.HttpParams;

/**
 * Mock implementation of {@link HttpResponse} suitable for unit tests.
 * It fakes an "OK" response from the server.
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.4.0
 */
public class MockOkResponse implements HttpResponse {
  private final StatusLine statusLine;
  private final HttpEntity payload;

  /**
   * Sets http status code 200.
   * @param payload the mock {@link HttpEntity} to set as payload
   * @since 0.4.0
   */
  public MockOkResponse(HttpEntity payload) {
    this.statusLine = new BasicStatusLine(
        new ProtocolVersion("HTTP", 1, 1), 
        200, 
        "OK"
    );
    this.payload = payload;
  }

  @Override
  public StatusLine getStatusLine() {
    return statusLine;
  }

  @Override
  public void setStatusLine(StatusLine statusline) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void setStatusLine(ProtocolVersion ver, int code) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void setStatusLine(ProtocolVersion ver, int code, String reason) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void setStatusCode(int code) throws IllegalStateException {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void setReasonPhrase(String reason) throws IllegalStateException {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public HttpEntity getEntity() {
    return payload;
  }

  @Override
  public void setEntity(HttpEntity entity) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public Locale getLocale() {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void setLocale(Locale loc) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public ProtocolVersion getProtocolVersion() {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public boolean containsHeader(String name) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public Header[] getHeaders(String name) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public Header getFirstHeader(String name) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public Header getLastHeader(String name) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public Header[] getAllHeaders() {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void addHeader(Header header) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void addHeader(String name, String value) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void setHeader(Header header) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void setHeader(String name, String value) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void setHeaders(Header[] headers) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void removeHeader(Header header) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void removeHeaders(String name) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public HeaderIterator headerIterator() {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public HeaderIterator headerIterator(String name) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public HttpParams getParams() {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }

  @Override
  public void setParams(HttpParams params) {
    throw new UnsupportedOperationException("Not supported yet."); //TODO implement
  }
}
