package com.duoshilin.android;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.duoshilin.android.week03.handler.HandlerTestActivity;
import com.duoshilin.android.week03.receiver.NetworkchangReceiver;

public class MainActivity extends AppCompatActivity {
    NetworkchangReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver = new NetworkchangReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        intentFilter.setPriority(100);
        registerReceiver(receiver,intentFilter);

        final Intent intent = new Intent("com.duoshilin.android.week03.MY_BROADCAST");
        sendBroadcast(intent);
//        sendOrderedBroadcast(intent,null);

        findViewById(R.id.to_page2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent1);
            }
        });

        findViewById(R.id.to_handler_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,HandlerTestActivity.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
