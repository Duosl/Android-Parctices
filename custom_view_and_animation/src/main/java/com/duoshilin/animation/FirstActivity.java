package com.duoshilin.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.duoshilin.custom_view.R;


public class FirstActivity extends AppCompatActivity {

    PopupWindow popupWindow;
    Button btn2;
    View v;
    int vWidth,vHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //View动画
        final View view = new View(this);
        v = findViewById(R.id.main);
        view.setBackground(getDrawable(R.drawable.meizi2));
        popupWindow = new PopupWindow(view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.pop_main);

        final Button btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAsDropDown(btn);
                }

            }
        });


        //ObjectAnimator
//        ObjectAnimator.ofArgb(btn2,"backgroundColor",0xFF000000, 0xFFFFFFFF).setDuration(2000).start();
//        ObjectAnimator.ofFloat(btn2,"rotationY", 180.0f, 360.0f).setDuration(2000).start();
//        ObjectAnimator.ofFloat(btn2,"rotationX", 180.0f, 360.0f).setDuration(2000).start();
//        ObjectAnimator.ofFloat(btn2,"x", 800).setDuration(2000).start();
//        ObjectAnimator.ofFloat(btn2,"y", 500).setDuration(2000).start();

        //ValueAnimator
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 800);
//        valueAnimator.setTarget(btn2);
        valueAnimator.setDuration(3000).start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int width = (int) animation.getAnimatedValue();
                btn2.setHeight(width);
            }
        });

//        final AnimatorSet animationSet = (AnimatorSet) AnimatorInflater.loadAnimator(FirstActivity.this,R.animator.property_animator);
//        animationSet.setTarget(btn2);
//        animationSet.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean isFirstFocus = true;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus&&isFirstFocus){
            isFirstFocus = false;
            vWidth = v.getMeasuredWidth();
            vHeight = v.getMeasuredHeight();
            ObjectAnimator.ofFloat(btn2,"x", (vWidth-btn2.getMeasuredWidth())/2).setDuration(2000).start();
            ObjectAnimator.ofFloat(btn2,"y", (vHeight-btn2.getMeasuredHeight())/2).setDuration(2000).start();
        }
    }
}
