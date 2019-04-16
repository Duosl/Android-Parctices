package com.duoshilin.rxjavaDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.duoshilin.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxjavaActivity extends AppCompatActivity {

    private static final String TAG = "RxjavaActivity";
    private TextView logView;
    private Button againBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        logView = findViewById(R.id.log_view);
        againBtn = findViewById(R.id.again_btn);
//        againBtn.setMovementMethod(ScrollingMovementMethod.getInstance());

        setUpRxJava();

        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpRxJava();
            }
        });

    }

    /**
     * 1、导入依赖包
     * 2、创建观察者(Observer)，并定义响应事件行为（或定义事件监听）
     * 3、创建被观察者(Observable)，并触发事件
     * 4、通过订阅(subscribe)连接观察者和被观察者
     */
    private void setUpRxJava() {
        //1、导入依赖包

        //2、创建观察者 Observer， 并定义事件响应行为
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: 开始采用subscribe连接");
                logView.append("---------------------------\n");
                logView.append("开始采用subscribe连接\n");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: 对Next时间做出响应 Value：" + integer);
                logView.append("对Next时间做出响应 Value:" + integer + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: 对Error时间做出响应,错误信息：" + e.getMessage());
                logView.append("onError: 对Error时间做出响应,错误信息：" + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: 对Complete时间做出响应");
                logView.append("对Complete时间做出响应\n");
            }
        };

        //3、创建被观察者 Observable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                //通过ObservableEmitter的对象产生事件并通知观察者
                //ObservableEmitter类介绍
                //定义：事件发射器
                //作用：定义需要发送的时间 & 向观察者发送事件
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();

            }
        });

        //3、通过订阅连接观察者和被观察者
        observable.subscribe(observer);

    }

}
