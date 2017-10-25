/* 
 * Copyright 2017 George Aristy (george.aristy@gmail.com).
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

package org.llorllale.youtrack.api.session;

import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * Signals that the attempted action is forbidden for the current user {@link Session session}.
 * 
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.1.0
 */
public class UnauthorizedException extends IOException {
  private static final long serialVersionUID = -2245180199390157205L;

  private final HttpResponse httpResponse;
  
  /**
   * Ctor.
   * 
   * @param message the exception message
   * @param httpResponse the {@link HttpResponse response} received
   * @since 0.1.0
   */
  public UnauthorizedException(String message, HttpResponse httpResponse) {
    super(message);
    this.httpResponse = httpResponse;
  }

  /**
   * The {@link HttpResponse response} received.
   * 
   * @return the {@link HttpResponse response} received
   * @since 0.1.0
   */
  public HttpResponse httpResponse() {
    return this.httpResponse;
  }
}