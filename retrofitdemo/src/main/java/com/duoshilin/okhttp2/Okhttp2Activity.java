package com.duoshilin.okhttp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.duoshilin.R;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 参考文章：http://liuwangshu.cn/application/network/5-okhttp2x.html
 */
public class Okhttp2Activity extends AppCompatActivity {

    private static final String TAG = "Okhttp2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp2);

        getAsyncHttp();
//        postAsyncHttp();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    getSyncHttp();
//                    postSyncHttp();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    //get同步请求 call.execute()
    private void getSyncHttp() throws IOException {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            Log.i(TAG, "getSyncHttp: 请求成功！" + response.code());
        } else {
            throw new IOException("请求失败：" + request.urlString());
        }
    }

    //get异步请求 call.enqueue()
    // 设置缓存
    private void getAsyncHttp() {
        OkHttpClient client = new OkHttpClient();
        //设置缓存
        File sdcache = getExternalCacheDir();
        Cache cache = new Cache(sdcache, 10 * 1024 * 1024);
        client.setCache(cache);
        //----------------------
        //设置超时时间
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(20, TimeUnit.SECONDS);

        final Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i(TAG, "getAsyncHttp: 请求失败：" + request.toString());
            }

            @Override
            public void onResponse(Response response) {

                if (response.cacheResponse() == null) {
                    Log.i(TAG, "getAsyncHttp--network: 请求成功：" + response);
                } else {
                    Log.i(TAG, "getAsyncHttp--cache: 请求成功：" + response);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Okhttp2Activity.this, "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //post异步请求
    private void postAsyncHttp() {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("i", "hello")
                .build();
        Request request = new Request.Builder()
                .url("http://fanyi.youdao.com/translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();
                Log.i(TAG, "postAsyncHttp: 请求成功：" + s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Okhttp2Activity.this, "postAsyncHttp--请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //post同步请求
    private void postSyncHttp() throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("i", "你好")
                .build();
        Request request = new Request.Builder()
                .url("http://fanyi.youdao.com/translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            Log.i(TAG, "postSyncHttp: 请求成功：" + response.body().string());
        } else {
            Log.i(TAG, "postSyncHttp: 请求失败！");
        }
    }

}
