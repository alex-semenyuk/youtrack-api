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

import org.llorllale.youtrack.api.session.Session;
import org.llorllale.youtrack.api.session.UnauthorizedException;

import java.io.IOException;

/**
 * A {@link FieldValue} that has sprung from a {@link AssignedField#change() change operation} that 
 * can apply itself as a change on the {@link Issue}.
 * 
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.8.0
 */
public interface SelectableFieldValue extends FieldValue {
  /**
   * Applies itself to the parent {@link AssignedField#issue() issue} and returns the updated issue.
   * 
   * @return the updated issue
   * @throws IOException if the server is unavailable
   * @throws UnauthorizedException if the user's {@link Session} is not authorized to perform
   *     this operation
   * @since 0.8.0
   * @see Issue#update(org.llorllale.youtrack.api.Field, org.llorllale.youtrack.api.FieldValue) 
   */
  public Issue apply() throws IOException, UnauthorizedException;
}