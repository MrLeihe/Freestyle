package com.sd.style.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Author: HeLei on 2017/9/11 23:17
 */

public class PercentView  extends View{

    private Paint mPaint;
    private RectF mOval;

    public PercentView(Context context) {
        super(context);
        init();
    }

    public PercentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mOval = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        Logger.d("mode--->" + mode + "----size--->" + size);
        int i = -1;
        switch (mode) {
            case MeasureSpec.AT_MOST :
                i = 0;
                break;
            case MeasureSpec.EXACTLY :
                i = 1;
                break;
            case MeasureSpec.UNSPECIFIED :
                i = 2;
                break;
        }
        Logger.d("i=====" + i);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
        int width = getWidth();
        int height = getHeight();
        int radius = width/4;
        Logger.e("height---->" + height + "----width---->" + width);
        canvas.drawCircle(width/2, width/2, width/4, mPaint);

        mPaint.setColor(Color.RED);
        mOval.set(width/2- radius, width/2- radius, width/2+ radius, width/ 2 + radius);
        canvas.drawArc(mOval, 0, 20, false, mPaint);

        mPaint.setColor(Color.BLUE);
        mOval.set(width/2- radius, width/2- radius, width/2+ radius, width/ 2 + radius);
        canvas.drawArc(mOval, 20, 90, false, mPaint);
    }
}
