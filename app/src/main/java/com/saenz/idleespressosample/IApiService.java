package com.saenz.idleespressosample;

import org.json.JSONArray;

/**
 * Created by Saenz on 5/5/15.
 */
public interface IApiService {
    void getUsers(final BaseCallback<JSONArray> callback);
}
