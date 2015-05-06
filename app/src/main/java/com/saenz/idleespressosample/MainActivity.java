package com.saenz.idleespressosample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.common.annotations.VisibleForTesting;
import org.json.JSONArray;


public class MainActivity extends ActionBarActivity {

    Button btnLoad, btnCount;
    TextView tvResult;
    int counter;
    IApiService mService;
    public static final String RS_TPL = "data count: %d";

    @VisibleForTesting
    void setService(IApiService service) {
        mService = service;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup(){
        counter = 1;
        tvResult = (TextView)findViewById(R.id.tv_result);
        btnLoad  = (Button)findViewById(R.id.btn_load);
        btnCount = (Button)findViewById(R.id.btn_count);

        mService = new ApiService();

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFromApi();
            }
        });

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter += 1;
                btnCount.setText(String.valueOf(counter));
            }
        });
    }

    private void loadFromApi(){
        isLoading(true);
        mService.getUsers(new BaseCallback<JSONArray>() {
            @Override
            public void success(JSONArray response) {
                isLoading(false);
                setupViewFromData(String.format(RS_TPL, response.length()));
            }
        });
    }

    private void setupViewFromData(String response){
        tvResult.setText(response);
    }

    private void isLoading(boolean isLoading){
        if(isLoading){
            tvResult.setText(getString(R.string.loading));
        }else{
            tvResult.setText("");
        }
    }

}
