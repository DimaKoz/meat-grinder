package com.kozhevin.rootchecks.util;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(AndroidJUnit4.class)
public class MeatGrinderTest {
    @Test
    public void testTryCatch() throws Exception {
        Field field0 = MeatGrinder.class.getDeclaredField("isLoaded");
        field0.setAccessible(true);
        field0.set(null, false);
        Field field = MeatGrinder.class.getDeclaredField("isUnderTest");
        field.setAccessible(true);
        field.set(null, true);
        assertThat(MeatGrinder.getInstance().isLibraryLoaded(), is(false));
        field.set(null, false);
        assertThat(MeatGrinder.getInstance().isLibraryLoaded(), is(true));
    }

}