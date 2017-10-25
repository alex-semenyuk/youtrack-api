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

package org.llorllale.youtrack.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Utility class to read all text content from an {@link InputStream} and
 * return it in string form.
 * 
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.1.0
 */
public class InputStreamAsString implements ExceptionalFunction<InputStream, String, IOException> {
  @Override
  public String apply(InputStream input) throws IOException {
    final String newLine = System.getProperty("line.separator");
    return new BufferedReader(new InputStreamReader(input, Charset.defaultCharset()))
        .lines()
        .reduce((line1,line2) -> line1.concat(newLine).concat(line2))
        .get()
        .trim();
  }
}
