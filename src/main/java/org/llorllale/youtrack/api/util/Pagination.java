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

package org.llorllale.youtrack.api.util;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * An {@link Iterator} that encapsulates a paginated resource from the YouTrack server.
 * 
 * <p>Note: the {@link #hasNext()} and {@link #next()} methods wrap checked exceptions inside
 * {@link UncheckedException}.</p>
 * 
 * @author George Aristy (george.aristy@gmail.com)
 * @param <T> the resource's type
 * @see Page
 * @since 0.7.0
 */
public final class Pagination<T> implements Iterator<T> {
  private final PageUri pageRequest;
  private final ExceptionalFunction<HttpEntity, Collection<T>, IOException> mapper;

  private Iterator<T> page;

  /**
   * Ctor.
   * 
   * @param pageRequest the supplier for each {@link Page page's} uri
   * @param mapper maps each page's URI into its corresponding contents
   * @since 0.7.0
   */
  public Pagination(
      PageUri pageRequest,
      ExceptionalFunction<HttpEntity, Collection<T>, IOException> mapper
  ) {
    this.pageRequest = pageRequest;
    this.mapper = mapper;
    this.page = new Page.Empty<>();
  }

  /**
   * Constructs a {@link PageUri} by providing: a) a {@link Counter} with values 0 and 
   * {@code pageSize}, and the {@code combiner}.
   * 
   * @param pageSize the page size
   * @param combiner the function that maps page numbers to http requests
   * @param mapper the function that maps the resources to collections
   * @see #Pagination(PageUri, ExceptionalFunction) 
   * @see PageUri
   * @since 1.0.0
   */
  public Pagination(
      int pageSize,
      Function<Integer, HttpUriRequest> combiner, 
      ExceptionalFunction<HttpEntity, Collection<T>, IOException> mapper
  ) {
    this(new PageUri(new Counter(0, pageSize), combiner), mapper);
  }

  @Override
  public boolean hasNext() {
    if (!this.page.hasNext()) {
      this.page = new Page<>(this.pageRequest.get(), this.mapper);
    }

    return this.page.hasNext();
  }

  @Override
  public T next() {
    if (this.hasNext()) {
      return this.page.next();
    }

    throw new NoSuchElementException();
  }
}
