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
import java.util.stream.Stream;

/**
 * A {@link Field} that is attached to an {@link Issue}.
 * 
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.8.0
 */
public interface AssignedField extends Field {
  /**
   * The {@link Issue} to which this {@link AssignedField} is attached to.
   * 
   * @return the {@link Issue} to which this {@link AssignedField} is attached to
   * @since 0.8.0
   */
  public Issue issue();

  /**
   * This field's {@link FieldValue value}.
   * 
   * @return this field's {@link FieldValue value}
   * @since 0.8.0
   */
  public FieldValue value();

  /**
   * Returns a stream of values that can be selected for this {@link AssignedField}.
   * 
   * @return a stream of values that can be selected for this {@link AssignedField}
   * @throws IOException if the server is unavailable
   * @throws UnauthorizedException if the user's {@link Session} is not authorized to perform
   *     this operation
   * @since 0.8.0
   */
  public Stream<SelectableFieldValue> change() throws IOException, UnauthorizedException;
}