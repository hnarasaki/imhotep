/*
 * Copyright (C) 2014 Indeed Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.indeed.flamdex.api;

public interface IntTermIterator extends TermIterator {
    /**
     * Resets this iterator, so that the next time next() is called it will be positioned at the first term that is >= provided term.  The iterator is
     * no longer valid until the next call to next()
     * @param term The term to reset the iterator to
     */
    public void reset(long term);

    /**
     * @return  the current term, invalid before next() is called or if next() returned false
     */
    public long term();
}
