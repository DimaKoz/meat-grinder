package com.kozhevin.rootchecks.ui;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kozhevin.rootchecks.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.kozhevin.rootchecks.constant.GeneralConst.GITHUB;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {

    private static final Uri INTENT_DATA_URI = Uri.parse(GITHUB);

    @Rule
    public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        intending(hasAction(Intent.ACTION_VIEW)).respondWith(
                new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void typeNumber_ValidInput_InitiatesCall() {
        onView(withId(R.id.action_github)).perform(click());
        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData(INTENT_DATA_URI)));
    }
    @After
    public void tearDown() throws Exception {

    }

}