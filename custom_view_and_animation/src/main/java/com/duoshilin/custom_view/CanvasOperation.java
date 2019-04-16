package com.duoshilin.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by duoshilin on 2018/12/29.
 */
public class CanvasOperation extends View {

    private Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;


    public CanvasOperation(Context context) {
        this(context,null);
    }

    public CanvasOperation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);

//        translate(canvas);
//        scale(canvas);
//        scale2(canvas);
//        rotate(canvas);
//        rotate2(canvas);
        skew(canvas);
    }

    //平移
    private void translate(Canvas canvas){
        canvas.drawCircle(0,0,200,mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.translate(300,300);
        canvas.drawCircle(0,0,200,mPaint);
    }

    //缩放、翻转
    /** 取值范围(n)	说明
        (-∞, -1)	先根据缩放中心放大n倍，再根据中心轴进行翻转
        -1	        根据缩放中心轴进行翻转
        (-1, 0)	    先根据缩放中心缩小到n，再根据中心轴进行翻转
        0	        不会显示，若sx为0，则宽度为0，不会显示，sy同理
        (0, 1)	    根据缩放中心缩小到n
        1	        没有变化
        (1, +∞)	    根据缩放中心放大n倍
     **/
    private void scale(Canvas canvas){
        //画出坐标系
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.translate(mWidth/2, mHeight/2);
        canvas.drawLine(0,-mHeight/2,0,mHeight/2, mPaint);
        canvas.drawLine(-mWidth/2,0,mWidth/2,0, mPaint);

        //画矩形
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        RectF rectF = new RectF(0,-400,400,0);
        canvas.drawRect(rectF,mPaint);
        mPaint.setColor(Color.RED);

        int saveCount = canvas.save();
        canvas.scale(.5f,.5f);
        canvas.drawRect(rectF,mPaint);

        canvas.restoreToCount(saveCount);
        saveCount = canvas.save();
        canvas.scale(-.5f,.5f, -200f,0);
        canvas.drawRect(rectF,mPaint);

        canvas.restoreToCount(saveCount);
        saveCount = canvas.save();
        canvas.scale(-.5f,-.5f, 0,0);
        canvas.drawRect(rectF,mPaint);

        canvas.restoreToCount(saveCount);
        saveCount = canvas.save();
        canvas.scale(.5f,-.5f, 400,0);
        canvas.drawRect(rectF,mPaint);

        canvas.restoreToCount(saveCount);
        mPaint.setColor(Color.GREEN);
        canvas.scale(-1.2f,-1.2f, 0,0);
        canvas.drawRect(rectF,mPaint);
    }

    //通过缩放效果实现效果
    private void scale2(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        canvas.translate(mWidth/2,mHeight/2);
        RectF rectF = new RectF(-400,-400,400,400);
        canvas.drawRect(rectF,mPaint);

        for (int i = 0; i < 20; i++) {
            canvas.scale(.9f,.9f);
            canvas.drawRect(rectF,mPaint);
        }
    }

    //旋转
    private void rotate(Canvas canvas){
        canvas.translate(mWidth/2,mHeight/2);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(0,0,0,400, mPaint);

        mPaint.setColor(Color.RED);
        canvas.rotate(90);//顺时针旋转90度
        canvas.drawLine(0,0,0,400, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.rotate(90,0,400);//顺时针旋转90度
        canvas.drawLine(0,0,0,400, mPaint);

        mPaint.setColor(Color.RED);
        canvas.rotate(90,0,0);//顺时针旋转90度
        canvas.drawLine(0,0,0,400, mPaint);
    }

    //旋转2
    private void rotate2(Canvas canvas){
        canvas.translate(mWidth/2,mHeight/2);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 10; i++) {
            canvas.drawCircle(0,0,400 - (40*i),mPaint);
        }

        for (int i = 0; i < 36; i++) {
            canvas.drawLine(0,400,0,360,mPaint);
            canvas.rotate(10);
        }
    }
    //错切
    private void skew(Canvas canvas){
        canvas.translate(mWidth/2,mHeight/2);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(0,0,200,200);
        canvas.drawRect(rectF,mPaint);

        mPaint.setColor(Color.RED);
        canvas.skew(0,1);
        canvas.skew(1,0);
//        canvas.skew(-1,1);
        canvas.drawRect(rectF,mPaint);
    }
}
