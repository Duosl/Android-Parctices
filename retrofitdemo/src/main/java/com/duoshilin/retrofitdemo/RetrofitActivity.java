package com.duoshilin.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.duoshilin.R;
import com.duoshilin.retrofitdemo.model.Translation;
import com.duoshilin.retrofitdemo.model.Translation1;
import com.duoshilin.retrofitdemo.service.TranslationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitActivity extends AppCompatActivity {

    private EditText editText;
    private Button button, button2;
    private TextView showTV;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        showTV = findViewById(R.id.showTV);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    translate(editText.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RetrofitActivity.this, "出现异常！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    translate1(editText.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RetrofitActivity.this, "出现异常！", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void translate(final String data) {

        //4、创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //5、创建网络请求接口实例，并配置网络请求的参数
        TranslationService service = retrofit.create(TranslationService.class);

        //6、发送网络请求
        Call<Translation> call = service.translate(data);

        //7、处理返回结果
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                String target = response.body().show();
                showTV.setText("");
                showTV.append(data + "\n");
                showTV.append(target == null ? data : target);
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Log.e(TAG, "连接服务器失败！ ");
                Log.e(TAG, t.getMessage());
                Toast.makeText(RetrofitActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void translate1(final String data) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TranslationService service = retrofit.create(TranslationService.class);
        Call<Translation1> call = service.translate1(data);
        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                showTV.setText("");
                showTV.setText(data + "\n");
                showTV.setText(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable t) {
                Log.e(TAG, "连接服务器失败！ ");
                Toast.makeText(RetrofitActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
