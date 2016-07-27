package com.example.sensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tianwenjie on 7/25/16.
 */
public class CameraHelpView extends View {
    //T 上 下 左 右 的变量
    private float mDeltaL;
    //左
    private float mDeltaR;
    //右
    private float mDeltaT;
    //上
    private float mDeltaB;
    //下


    Paint paint1;

    public CameraHelpView(Context context) {
        super(context);
        init();
    }

    public CameraHelpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // 绘制画笔
    private void init() {
        paint1 = new Paint();
        paint1.setStrokeWidth(6);
    }


    public void setStartL(float startl) {
        this.mDeltaL = startl;
        // 绘制画布
        invalidate();
    }

    public void setStartR(float startr) {
        this.mDeltaR = startr;
        invalidate();
    }

    public void setStartT(float startt) {
        this.mDeltaT = startt;
        invalidate();
    }

    public void setStartB(float startb) {
        this.mDeltaB = startb;
        invalidate();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {

        Point c = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);
        //中心点
        PointF l = new PointF((canvas.getWidth() / 2 * (mDeltaL / 100f)), canvas.getHeight() / 2);
        //左 结束点
        PointF r = new PointF((canvas.getWidth() / 2 * ((100 - mDeltaR) / 100f )+ canvas.getWidth() / 2), canvas.getHeight() / 2);
        //右 结束点
        PointF t = new PointF(canvas.getWidth() / 2, (canvas.getHeight() / 2) * (mDeltaT / 100f));
        //上 结束点
        PointF b = new PointF(canvas.getWidth() / 2, (canvas.getHeight() / 2) * ((100 - mDeltaB) / 100f) + canvas.getHeight() / 2);
        //下 结束点

        paint1.setColor(Color.BLACK);
        canvas.drawLine(c.x, c.y, l.x, l.y, paint1);
        //左
        paint1.setColor(Color.BLACK);
        canvas.drawLine(c.x, c.y, r.x, r.y, paint1);
        //右
        paint1.setColor(Color.BLACK);
        canvas.drawLine(c.x, c.y, t.x, t.y, paint1);
        //上
        paint1.setColor(Color.BLACK);
        canvas.drawLine(c.x, c.y, b.x, b.y, paint1);
        //下
    }
}

