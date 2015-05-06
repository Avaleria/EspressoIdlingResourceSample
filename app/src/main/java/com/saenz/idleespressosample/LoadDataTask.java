package com.saenz.idleespressosample;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * Created by Saenz on 5/5/15.
 */
public class LoadDataTask extends AsyncTask<Void, Void, String> {

    BaseCallback<String> mCallback;
    Context mCtx;

    public LoadDataTask(Context ctx, BaseCallback<String> callback) {
        mCtx = ctx;
        mCallback = callback;
        MainActivity activity = new MainActivity();
        activity.setService(new ApiService());
    }

    @Override
    protected final String doInBackground(Void... params) {
        SystemClock.sleep(3 * 1000);
        return mCtx.getString(R.string.data_msg);
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.success(result);
    }
}
