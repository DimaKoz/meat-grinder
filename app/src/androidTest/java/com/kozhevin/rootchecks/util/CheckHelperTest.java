package com.kozhevin.rootchecks.util;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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