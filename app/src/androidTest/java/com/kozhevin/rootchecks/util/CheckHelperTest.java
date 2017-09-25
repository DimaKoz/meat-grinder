package com.kozhevin.rootchecks.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kozhevin.rootchecks.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.kozhevin.rootchecks.util.ChecksHelper.getFound;
import static com.kozhevin.rootchecks.util.ChecksHelper.getNonCheck;
import static com.kozhevin.rootchecks.util.ChecksHelper.getOk;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
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
@RunWith(AndroidJUnit4.class)
public class CheckHelperTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void testBitmapGetters() throws Exception {
        Context ctx = mActivityRule.getActivity();
        Bitmap testBitmap = getNonCheck(ctx);
        assertNotNull(testBitmap);
        testBitmap.recycle();
        testBitmap = getNonCheck(ctx);
        assertThat(testBitmap.isRecycled(), is(false));

        testBitmap = getFound(ctx);
        assertNotNull(testBitmap);
        testBitmap.recycle();
        testBitmap = getFound(ctx);
        assertThat(testBitmap.isRecycled(), is(false));

        testBitmap = getOk(ctx);
        assertNotNull(testBitmap);
        testBitmap.recycle();
        testBitmap = getOk(ctx);
        assertThat(testBitmap.isRecycled(), is(false));

    }
}