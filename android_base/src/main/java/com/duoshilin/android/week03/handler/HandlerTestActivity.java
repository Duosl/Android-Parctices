package com.duoshilin.android.week03.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.duoshilin.android.R;

public class HandlerTestActivity extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

        tv=findViewById(R.id.tv);

        method1();
        method2();
        method3();


    }
    //匿名内部类实现
    private void method1(){
        final Handler mHandler;
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle data = msg.getData();
                tv.setText(data.getString("key"));
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.obj = "匿名实现的Handler";
                Bundle data = new Bundle();
                data.putString("key","value");
                message.setData(data);
                mHandler.sendMessage(message);
            }
        }).start();
    }

    //新建Handler子类实现
    private void method2(){
        final Handler mHandler;
        class MyHandler extends Handler{
            @Override
            public void handleMessage(Message msg) {
                tv.setText(msg.obj.toString());
            }
        }
        mHandler = new MyHandler();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Message message = new Message();
                message.obj = "Handler子类实现的Handler";
                mHandler.sendMessageDelayed(message,2000);
            }
        }).start();
    }

    //使用Handler的post()实现
    private void method3(){
        Handler mHandler;
        mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setText("使用handler.post()实现");
            }
        },3000);
    }
}
