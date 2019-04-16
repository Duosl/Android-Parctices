package com.duoshilin.android.week03.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by duoshilin on 2018/12/26.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "这是我自己定义的广播", Toast.LENGTH_SHORT).show();
    }
}
