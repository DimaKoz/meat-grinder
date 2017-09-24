package com.kozhevin.rootchecks.util;

import com.kozhevin.rootchecks.R;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_BUSYBOX_BINARY;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_DANGEROUS_PROPS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_DEV_KEYS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_HOOKS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_NON_RELEASE_KEYS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_PERMISSIVE_SELINUX;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_RESETPROP;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SUPER_USER_APK;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SU_BINARY;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SU_EXISTS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_TEST_KEYS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_UNKNOWN;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_WRONG_PATH_PERMITION;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_XPOSED;
import static com.kozhevin.rootchecks.util.ChecksHelper.getCheckStringId;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
public class ChecksHelperTest {

    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<ChecksHelper> constructor = ChecksHelper.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        assertTrue (Modifier.isPublic(ChecksHelper.class.getModifiers()));
        constructor.setAccessible(true);
        Object obj = constructor.newInstance();
        assertNotNull(obj);
        assertThat(constructor.newInstance(), isA(ChecksHelper.class));
    }

    @Test
    public void getCheckStringIdTest() throws Exception {
        assertThat(getCheckStringId(CH_TYPE_TEST_KEYS), is(R.string.checks_desc_1));
        assertThat(getCheckStringId(CH_TYPE_DEV_KEYS), is(R.string.checks_desc_2));
        assertThat(getCheckStringId(CH_TYPE_NON_RELEASE_KEYS), is(R.string.checks_desc_3));
        assertThat(getCheckStringId(CH_TYPE_DANGEROUS_PROPS), is(R.string.checks_desc_4));
        assertThat(getCheckStringId(CH_TYPE_PERMISSIVE_SELINUX), is(R.string.checks_desc_5));
        assertThat(getCheckStringId(CH_TYPE_SU_EXISTS), is(R.string.checks_desc_6));
        assertThat(getCheckStringId(CH_TYPE_SUPER_USER_APK), is(R.string.checks_desc_7));
        assertThat(getCheckStringId(CH_TYPE_SU_BINARY), is(R.string.checks_desc_8));
        assertThat(getCheckStringId(CH_TYPE_BUSYBOX_BINARY), is(R.string.checks_desc_9));
        assertThat(getCheckStringId(CH_TYPE_XPOSED), is(R.string.checks_desc_10));
        assertThat(getCheckStringId(CH_TYPE_RESETPROP), is(R.string.checks_desc_11));
        assertThat(getCheckStringId(CH_TYPE_WRONG_PATH_PERMITION), is(R.string.checks_desc_12));
        assertThat(getCheckStringId(CH_TYPE_HOOKS), is(R.string.checks_desc_13));
        assertThat(getCheckStringId(CH_TYPE_UNKNOWN), is(R.string.empty));
        //noinspection WrongConstant
        assertThat(getCheckStringId(Integer.MAX_VALUE), is(R.string.empty));


    }

}