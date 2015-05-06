package com.saenz.idleespressosample;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;


/**
 * Created by Saenz on 5/5/15.
 */
public class ApiService implements IApiService {
    private AsyncHttpClient mClient;
    private static final String API = "http://jsonplaceholder.typicode.com/comments";

    public ApiService(){
        mClient = new AsyncHttpClient();
    }

    public void getUsers(final BaseCallback<JSONArray> callback){
        mClient.get(API, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                callback.success(response);
            }
        });
    }

}
