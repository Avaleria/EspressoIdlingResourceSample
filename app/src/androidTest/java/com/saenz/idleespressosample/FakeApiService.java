package com.saenz.idleespressosample;

import android.support.test.espresso.contrib.CountingIdlingResource;

import org.json.JSONArray;


/**
 * Created by Saenz on 5/5/15.
 */
public class FakeApiService implements IApiService {

    ApiService mRealService;
    private CountingIdlingResource mIdlingResource;

    public FakeApiService(CountingIdlingResource idlingResource) {
        mIdlingResource = idlingResource;
        mRealService = new ApiService();
    }

    @Override
    public void getUsers(final BaseCallback<JSONArray> callback) {
        mIdlingResource.increment();
        mRealService.getUsers(new BaseCallback<JSONArray>() {
            @Override
            public void success(JSONArray response) {
                callback.success(response);
                mIdlingResource.decrement();
            }
        });
    }
}
