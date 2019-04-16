package com.duoshilin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duoshilin.okhttp2.Okhttp2Activity;
import com.duoshilin.okhttp3.Okhttp3Activity;
import com.duoshilin.retrofitdemo.RetrofitActivity;
import com.duoshilin.rxjavaDemo.RxjavaActivity;

public class HomeActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button1 = findViewById(R.id.retrofitBtn);
        button2 = findViewById(R.id.rxjavaBtn);
        button3 = findViewById(R.id.okhttpBtn);
        button4 = findViewById(R.id.okhttp3Btn);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RetrofitActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RxjavaActivity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Okhttp2Activity.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Okhttp3Activity.class);
                startActivity(intent);
            }
        });
    }
}
