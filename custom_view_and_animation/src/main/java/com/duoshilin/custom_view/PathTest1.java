package com.duoshilin.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by duoshilin on 2019/1/2.
 */
public class PathTest1 extends View {

    private int mWidth;
    private int mHeight;

    Path mPath = new Path();
    Paint mPaint = new Paint();
    float r = 80;
    int count = 6;
    float angle = (float) (2 * Math.PI / count);

    public PathTest1(Context context) {
        this(context, null);
    }

    public PathTest1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //----------------init paint------------------//
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//
//        canvas.translate(mWidth / 2, mHeight / 2);
//        canvas.drawLines(new float[]{
//                -mWidth / 2, 0, mWidth / 2, 0,
//                0, -mHeight / 2, 0, mHeight / 2,
//        }, paint);
//
//        Path path = new Path();
//        paint.setStrokeWidth(10);
//        paint.setColor(Color.RED);

        //----------------init paint------------------//

//        path.lineTo(200,200);
//        path.lineTo(200,0);
//        canvas.drawPath(path,paint);

//        path.lineTo(200,200);
//        path.moveTo(200,100);
//        path.lineTo(200,0);
//        canvas.drawPath(path,paint);

//        path.lineTo(200,200);
//        path.setLastPoint(200,100);
//        path.lineTo(200,0);
//        path.close();//将path闭合，将最后一个点与第一个点连接起来
//        canvas.drawPath(path,paint);

//        RectF rectF = new RectF(-200,-200,200,200);
////        path.addRect(rectF,Path.Direction.CW);
////        path.addRect(rectF,Path.Direction.CCW);
////        path.setLastPoint(-400,400);
////        canvas.drawPath(path,paint);
//
//        path.addOval(rectF,Path.Direction.CCW);
//        path.setLastPoint(200,200);
//        Path path2 = new Path();
//        path2.addRect(0,0,200,200,Path.Direction.CCW);
//        path2.offset(200,200);
//        path.addPath(path2);
////        path.addPath(path2,-200,-200);
//        canvas.drawPath(path,paint);

//        RectF rectF = new RectF(0,0,300,300);
//        canvas.scale(1,-1); //x正方向：右→ y正方向：上↑
//        path.lineTo(150,150);//移动到(150，150)
//        path.addArc(rectF,0,270);//画圆弧
////        path.arcTo(rectF,0,270,true);//与上一句等价
//        canvas.drawPath(path,paint);

//        RectF rectF = new RectF(-200,-200,200,200);
//        canvas.scale(1,-1); //x正方向：右→ y正方向：上↑
//        path.lineTo(0,0);//移动到(150，150)
//        path.arcTo(rectF,0,270);//画圆弧
////        path.arcTo(rectF,0,270,false);//与上一句等价
//        path.lineTo(200,0);
//        canvas.drawPath(path,paint);

//        path.addCircle(0,0,100, Path.Direction.CW);
//        Path dst = new Path();                      // dst中添加一个矩形
//        dst.addRect(-200,-200,200,200, Path.Direction.CW);
//        path.offset(300,0,dst);                     // 平移
//        canvas.drawPath(path,paint);               // 绘制path
//        paint.setColor(Color.BLUE);                // 更改画笔颜色
//        canvas.drawPath(dst,paint);

        //demo练习 -- Android雷达图(蜘蛛网图)绘制
//        path.moveTo(-50,0);
//        for (int i = 0; i < 6; i++) {
//            float x = (float) (0 + 50 * Math.cos(60*i));
//            float y = (float) (0 + 50 * Math.sin(60*i));
//            path.lineTo(x,y);
//        }

        drawPolygon(canvas);
        drawLines(canvas);
        drawRegion(canvas);
        drawText(canvas);

    }

    //画出N边形
    private void drawPolygon(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);

        for (int i = 1; i < 6; i++) {
            float currentR = r * i;
            mPath.moveTo(currentR, 0);
            for (int j = 0; j < count; j++) {
                float x = (float) (currentR * Math.cos(angle * j));
                float y = (float) (currentR * Math.sin(angle * j));
                mPath.lineTo(x, y);
            }
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
    }

    private void drawLines(Canvas canvas) {
        float maxR = r * 5;
        for (int i = 0; i < 6; i++) {
            mPath.moveTo(0, 0);
            float x = (float) (maxR * Math.cos(angle * i));
            float y = (float) (maxR * Math.sin(angle * i));
            mPath.lineTo(x, y);
        }
        canvas.drawPath(mPath, mPaint);
    }

    //绘制覆盖区域
    private void drawRegion(Canvas canvas) {
        double[] data = {100, 60, 80, 60, 100, 30, 10, 20}; //各维度分值
        float maxValue = 100;             //数据最大值

        float maxR = r * 5;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setAlpha(127);
        mPath.reset();
        for (int i = 0; i < count; i++) {
            float percent = (float) (data[i] / maxValue);
            float x = (float) (maxR * Math.cos(angle * i) * percent);
            float y = (float) (maxR * Math.sin(angle * i) * percent);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.BLACK);
            canvas.drawCircle(x, y, 10, mPaint);

            if (i == 0) {
                mPath.moveTo(x, y);
            } else {
                mPath.lineTo(x, y);
            }
        }
        canvas.drawPath(mPath, paint);
    }

    private void drawText(Canvas canvas) {
        double[] data = {100, 60, 80, 60, 100, 30, 10, 20}; //各维度分值
        float maxValue = 100;             //数据最大值
        String[] titles = {"战士","坦克","法师","刺客","辅助","射手"};

        float maxR = r * 5;
        Paint textPaint = new Paint();
        textPaint.setTextSize(40);

        mPath.reset();
        for (int i = 0; i < count; i++) {
            float x = (float) (maxR * Math.cos(angle * i));
            float y = (float) (maxR * Math.sin(angle * i));

            if (x >= 0) {
                if(y>0){
                    canvas.drawText(titles[i], x+10, y+40, textPaint);
                }else if (y<0){
                    canvas.drawText(titles[i], x+10, y-10, textPaint);
                }else {
                    canvas.drawText(titles[i], x+20, y+10, textPaint);
                }
            } else {
                float textWidth = textPaint.measureText(titles[i]);
                if(y>0){
                    canvas.drawText(titles[i], x-textWidth-10, y+40, textPaint);
                }else if (y<0){
                    canvas.drawText(titles[i], x-textWidth-10, y-10, textPaint);
                }else {
                    canvas.drawText(titles[i], x-textWidth-20, y+10, textPaint);
                }
            }

        }
    }
}
