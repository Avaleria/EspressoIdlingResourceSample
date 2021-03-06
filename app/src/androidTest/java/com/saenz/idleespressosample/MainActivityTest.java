package com.saenz.idleespressosample;

import android.support.test.espresso.contrib.CountingIdlingResource;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Saenz on 5/5/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        MainActivity activity = getActivity();
        setupService(activity);
    }

    private void setupService(MainActivity activity){
        CountingIdlingResource idlingResource =
                new CountingIdlingResource(ApiService.class.getSimpleName());
        IApiService realService = new ApiService();
        IApiService fakeService = new FakeApiService(realService, idlingResource);
        activity.setService(fakeService);
        registerIdlingResources(idlingResource);
    }

    public void testShouldShowDataFromApiAfterClickOnLoadButton(){
        onView(withId(R.id.btn_load)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_result)).check(matches(withText(R.string.hello_world)));

        onView(withId(R.id.btn_load)).perform(click());

        final int EXPECTED_COUNT = 500;
        final String EXPECTED_MSG = String.format(MainActivity.RS_TPL, EXPECTED_COUNT);

        onView(withId(R.id.tv_result)).check(matches(withText(EXPECTED_MSG)));
    }
}