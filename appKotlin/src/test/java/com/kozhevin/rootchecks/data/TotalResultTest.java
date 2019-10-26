package com.kozhevin.rootchecks.data;

import com.kozhevin.rootchecks.constant.GeneralConst;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2017  Dmitrii Kozhevin <kozhevin.dima@gmail.com>
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class TotalResultTest {
    TotalResult totalResult;
    public TotalResultTest() {
        List<CheckInfo> list = new ArrayList<>();
        totalResult = new TotalResult(list, GeneralConst.INSTANCE.getCH_STATE_CHECKED_ROOT_NOT_DETECTED());
    }
    @Test
    public void getList() throws Exception {
        assertEquals(totalResult.getList().size(), 0);
    }

    @Test
    public void getCheckState() throws Exception {
        assertEquals(totalResult.getCheckState(), GeneralConst.INSTANCE.getCH_STATE_CHECKED_ROOT_NOT_DETECTED());
        assertNotEquals(totalResult.getCheckState(), GeneralConst.INSTANCE.getCH_STATE_CHECKED_ERROR());
        assertNotEquals(totalResult.getCheckState(), GeneralConst.INSTANCE.getCH_STATE_CHECKED_ROOT_DETECTED());
        assertNotEquals(totalResult.getCheckState(), GeneralConst.INSTANCE.getCH_STATE_STILL_GOING());
        assertNotEquals(totalResult.getCheckState(), GeneralConst.INSTANCE.getCH_STATE_UNCHECKED());
    }

}