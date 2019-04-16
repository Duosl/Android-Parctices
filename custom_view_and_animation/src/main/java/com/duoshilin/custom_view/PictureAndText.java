package com.duoshilin.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by duoshilin on 2019/1/2.
 */
public class PictureAndText extends View {

    private Picture mPicture = new Picture();
    Paint mPaint = new Paint();

    public PictureAndText(Context context) {
        this(context,null);
    }

    public PictureAndText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        recoding();
    }

    private void recoding(){
        Canvas canvas = mPicture.beginRecording(500,500);

        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(250,250,100,mPaint);

        mPicture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //-------------------------------Picture-----------------------------------//

        //第一种方法：这种方法在比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用
//        mPicture.draw(canvas);

        //第二种方法：绘制的内容根据选区进行了缩放。
//        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),250));

        //推荐使用
        //第三种方法：此处setBounds是设置在画布上的绘制区域，并非根据该区域进行缩放，也不是剪裁Picture，每次都从Picture的左上角开始绘制。
        PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
        pictureDrawable.setBounds(0,0,mPicture.getWidth(),250);
        pictureDrawable.draw(canvas);


        //-------------------------------Text-----------------------------------//
        canvas.translate(getWidth()/2,getHeight()/2);
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextAlign(Paint.Align.CENTER);
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("ABCDEF",0,0,mPaint);
//        canvas.drawText("ABCDEF",1,3,0,0,mPaint);

        canvas.drawPosText("ABCDEF",new float[]{
                -300,300,
                -200,200,
                -100,100,
                100,100,
                200,200,
                300,300
        },mPaint);

//        canvas.drawTextOnPath();
    }
}
