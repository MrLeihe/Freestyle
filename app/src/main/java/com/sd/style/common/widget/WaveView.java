package com.sd.style.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: HeLei on 2017/10/24 16:57
 * 水波纹view
 */

public class WaveView extends View {


    private long mDuration;
    private float minWaveRadius;
    private float maxWaveRadius;
    private Paint paint;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public void setMinWaveRadius(float minWaveRadius) {
        this.minWaveRadius = minWaveRadius;
    }

    public void setMaxWaveRadius(float maxWaveRadius) {
        this.maxWaveRadius = maxWaveRadius;
    }

    private class Circle{
        //圆创建的时间
        private long mCreateTime;

        public Circle() {
            mCreateTime = System.currentTimeMillis();
        }

        public int getAlpha(){
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return (int) ((1.0f - percent) * 255);
        }

        public float getWaveRadius(){
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return minWaveRadius + percent * (maxWaveRadius - minWaveRadius);
        }
    }
}
