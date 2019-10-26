package com.kozhevin.rootchecks.ui;

import androidx.test.annotation.UiThreadTest;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.kozhevin.rootchecks.R;
import com.kozhevin.rootchecks.data.CheckInfo;
import com.kozhevin.rootchecks.data.TotalResult;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ERROR;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_DETECTED;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_NOT_DETECTED;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_STILL_GOING;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_UNCHECKED;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.Is.isA;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    MainActivity activity;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        activity = mActivityRule.getActivity();
    }

    @UiThreadTest
    @Test
    public void testOnProcessFinishedAndOnUpdateResult() {
        mActivityRule.getActivity().onUpdateResult(null);
        mActivityRule.getActivity().onProcessFinished(null);
        ArrayList<CheckInfo> list = new ArrayList<>();
        TotalResult totalResult = new TotalResult(list, CH_STATE_UNCHECKED);
        activity.onProcessFinished(totalResult);
        totalResult = new TotalResult(list, CH_STATE_CHECKED_ROOT_NOT_DETECTED);
        activity.onProcessFinished(totalResult);
        totalResult = new TotalResult(list, CH_STATE_CHECKED_ERROR);
        activity.onProcessFinished(totalResult);
        totalResult = new TotalResult(list, CH_STATE_STILL_GOING);
        activity.onProcessFinished(totalResult);
        totalResult = new TotalResult(list, CH_STATE_CHECKED_ROOT_DETECTED);
        activity.onProcessFinished(totalResult);
        //noinspection WrongConstant
        totalResult = new TotalResult(list, Integer.MAX_VALUE);
        Exception exception = null;
        try {
            activity.onProcessFinished(totalResult);
        } catch (IllegalStateException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
    }

    @UiThreadTest
    @Test
    public void testOnOptionsItemSelected() {
        @SuppressWarnings("RestrictedApi")
        MenuBuilder bld = new MenuBuilder(activity);
        @SuppressWarnings("RestrictedApi")
        MenuItem item = bld.add("test");
        activity.onOptionsItemSelected(item);
    }

    @UiThreadTest
    @Test
    public void testAdapter() {
        ResultAdapter adapter = (ResultAdapter) ((RecyclerView) activity.findViewById(R.id.list)).getAdapter();
        Assert.assertNotNull(adapter.getItem(adapter.getItemCount()-1));
        Assert.assertThat(adapter.getItemId(adapter.getItemCount()-1), isA(Long.class));
    }

    @UiThreadTest
    @Test
    public void testResultItemHolder() {
        ResultItemHolder holder = new ResultItemHolder(
                LayoutInflater.from(activity).inflate(R.layout.item_check, null, false));


        ResultAdapter adapter = (ResultAdapter) ((RecyclerView) activity.findViewById(R.id.list)).getAdapter();
        int position = adapter.getItemCount()-1;
        CheckInfo item =  adapter.getItem(position);
        holder.onBind(item, position);
        holder.onBind(item, position);

    }

    @Test
    public void testFabButtonAndList() {
        IdlingResource ir = new RecyclerViewScrollingIdlingResource((RecyclerView) activity.findViewById(R.id.list));
        IdlingRegistry.getInstance().register(ir);
        Matcher listMatcher = withId(R.id.list);
        onView(listMatcher).perform(smoothScrollTo(12));
        onView(withId(R.id.fab)).perform(click());
        onView(listMatcher).perform(smoothScrollTo(0));
        onView(withId(R.id.fab)).perform(click());
        IdlingRegistry.getInstance().unregister(ir);
    }

    private static ScrollToPositionViewAction smoothScrollTo(int position) {
        return new ScrollToPositionViewAction(position);
    }

    private static final class ScrollToPositionViewAction implements ViewAction {
        private final int position;

        private ScrollToPositionViewAction(int position) {
            this.position = position;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Matcher<View> getConstraints() {
            return allOf(isAssignableFrom(RecyclerView.class), isDisplayed());
        }

        @Override
        public String getDescription() {
            return "smooth scroll RecyclerView to position: " + position;
        }

        @Override
        public void perform(UiController uiController, View view) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.smoothScrollToPosition(position);
        }
    }

    public static class RecyclerViewScrollingIdlingResource implements IdlingResource {
        private ResourceCallback resourceCallback;
        private RecyclerView recyclerView;

        public RecyclerViewScrollingIdlingResource(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Override
        public String getName() {
            return RecyclerViewScrollingIdlingResource.class.getName();
        }

        @Override
        public boolean isIdleNow() {
            boolean isIdle = !recyclerView.getLayoutManager().isSmoothScrolling();
            if (isIdle) {
                resourceCallback.onTransitionToIdle();
            }
            return isIdle;
        }

        @Override
        public void registerIdleTransitionCallback(
                IdlingResource.ResourceCallback resourceCallback) {
            this.resourceCallback = resourceCallback;
        }
    }
}