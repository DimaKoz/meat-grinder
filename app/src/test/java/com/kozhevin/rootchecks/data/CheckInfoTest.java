package com.kozhevin.rootchecks.data;

import com.kozhevin.rootchecks.constant.GeneralConst;

import org.junit.Test;

import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_DEV_KEYS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_HOOKS;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

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
public class CheckInfoTest {

    private CheckInfo checkInfo;

    public CheckInfoTest() {
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_UNKNOWN);
    }


    @Test
    public void getTypeCheck() throws Exception {
        checkInfo.setTypeCheck(CH_TYPE_HOOKS);
        assertEquals(checkInfo.getTypeCheck(), CH_TYPE_HOOKS);
    }

    @Test
    public void equalsTest() throws Exception {
        checkInfo.setState(null);
        assertThat(checkInfo.equals(checkInfo), is(true));
        assertThat(checkInfo.equals(this), is(false));
        assertThat(checkInfo.equals(null), is(false));
        checkInfo.setTypeCheck(GeneralConst.CH_TYPE_UNKNOWN);
        assertThat(checkInfo.equals(new CheckInfo(null, GeneralConst.CH_TYPE_UNKNOWN)), is(true));
        assertThat(checkInfo.equals(new CheckInfo(null, GeneralConst.CH_TYPE_NON_RELEASE_KEYS)), is(false));
        assertThat(checkInfo.equals(new CheckInfo(true, GeneralConst.CH_TYPE_UNKNOWN)), is(false));
    }

    @Test
    public void hashCodeTest() throws Exception {
        checkInfo.setState(true);
        checkInfo.setTypeCheck(CH_TYPE_DEV_KEYS);
        CheckInfo chInfo = new CheckInfo(true,CH_TYPE_DEV_KEYS);
        assertEquals(checkInfo.hashCode(), chInfo.hashCode());
        chInfo.setTypeCheck(CH_TYPE_HOOKS);
        assertNotEquals(checkInfo.hashCode(), chInfo.hashCode());
        chInfo.setState(null);
        checkInfo.setState(null);
        checkInfo.setTypeCheck(CH_TYPE_HOOKS);
        assertEquals(checkInfo.hashCode(), chInfo.hashCode());
    }

    @Test
    public void getSetState() throws Exception {
        checkInfo.setState(null);
        assertThat(checkInfo.getState(), is((Boolean) null));
        checkInfo.setState(true);
        assertThat(checkInfo.getState(), is(Boolean.TRUE));
        checkInfo.setState(false);
        assertThat(checkInfo.getState(), is(Boolean.FALSE));
    }

}