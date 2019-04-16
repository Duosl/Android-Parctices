package com.duoshilin.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 饼状图
 * Created by duoshilin on 2018/12/29.
 */
public class PieChartView extends View {

    private List<Data> datas = new ArrayList<>();

    private Paint mPaint = new Paint();

    private float currentAngle = 0; //默认从0度开始画

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initDatas();
    }

    private void initDatas() {
        datas.add(new Data("优秀", 10));
        datas.add(new Data("普通", 20));
        datas.add(new Data("良好", 20));
        datas.add(new Data("及格", 20));
        datas.add(new Data("不及格", 5));
        analyzeData();
    }

    private void analyzeData() {
        int sum = 0;
        for (Data date : datas) {
            sum += date.value;
        }
        for (Data date : datas) {
            date.setPercentage(date.value/sum);
        }
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < datas.size(); i++) {
            drawArc(canvas,datas.get(i).percentage,getRandomColor());
        }
    }

    //画圆弧
    private void drawArc(Canvas canvas, float percentage, int color) {
        mPaint.setColor(getRandomColor());
        float sweepAngle = transformToAngle(percentage);
        RectF oval = new RectF(400, 500, 1000, 1100);
        canvas.drawArc(oval, currentAngle, sweepAngle, true, mPaint);
        currentAngle += sweepAngle;
//        if (currentAngle > 360) {
//            throw new IllegalStateException("the angle could not overflow 360");
//        }
    }

    //将百分比转换为角度
    private float transformToAngle(float percentage) {
        return 360 * percentage;
    }

    //获取随机的颜色
    private Integer getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.rgb(r, g, b);
    }

    class Data {
        private String name;
        private float value;
        private float percentage;

        public Data(String name, float value) {
            this.name = name;
            this.value = value;
        }

        public float getPercentage() {
            return percentage;
        }

        public void setPercentage(float percentage) {
            this.percentage = percentage;
        }
    }

}
