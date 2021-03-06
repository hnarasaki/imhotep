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
 package com.indeed.imhotep.metrics;

import com.indeed.flamdex.api.IntValueLookup;

import java.util.Arrays;

/**
 * @author jsgroth
 */
public class Constant implements IntValueLookup {
    private final long val;

    public Constant(long val) {
        this.val = val;
    }

    @Override
    public long getMin() {
        return val;
    }

    @Override
    public long getMax() {
        return val;
    }

    @Override
    public void lookup(int[] docIds, long[] values, int n) {
        Arrays.fill(values, 0, n, val);
    }

    @Override
    public long memoryUsed() {
        return 0L;
    }

    @Override
    public void close() {
    }
}
