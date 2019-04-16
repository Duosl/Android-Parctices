package com.meituan.android.okhttp_mt_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.meituan.android.okhttp_mt_demo.interceptors.GzipRequestInterceptor;
import com.meituan.android.okhttp_mt_demo.interceptors.LoggingInterceptor;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String url = "http://www.publicobject.com/helloworld.txt";
    private static final String TAG = "MainActivity";

    private Button getSyncBtn, getAsyncBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
    }

    private void initComponent() {
        getSyncBtn = findViewById(R.id.get_sync_btn);
        getAsyncBtn = findViewById(R.id.get_async_btn);

        getSyncBtn.setOnClickListener(this);
        getAsyncBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_sync_btn:
                getSyncHttp();
                break;
            case R.id.get_async_btn:
                getAsyncHttp();
                break;
            default:
                break;
        }
    }

    private void getSyncHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
//                client.interceptors().add(new LoggingInterceptor());
//                client.interceptors().add(new GzipRequestInterceptor());
                client.networkInterceptors().add(new LoggingInterceptor());
                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        Log.d(TAG, "getSyncHttp-success: " + response.body().string());
                    } else {
                        Log.d(TAG, "getSyncHttp-onFailure ");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getAsyncHttp() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

}
