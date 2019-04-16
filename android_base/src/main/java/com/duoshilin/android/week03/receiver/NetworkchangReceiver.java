package com.duoshilin.android.week03.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by duoshilin on 2018/12/26.
 */
public class NetworkchangReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
        }

    }
}
