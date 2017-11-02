package com.sd.style.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.facebook.stetho.common.LogUtil;
import com.sd.style.R;
import com.sd.style.common.uitls.ContextCompactUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: HeLei on 2017/10/24 16:57
 * 水波纹view
 */

public class WaveView extends View {

    private static final int CREATE_CIRCLE = 0;
    //每个圆存在的时间
    private long mDuration = 2000;
    //最小半径
    private float minWaveRadius;
    //最大半径
    private float maxWaveRadius;
    private Paint paint;
    //是否主动设置过最大半径
    private boolean isSetMaxRadius;
    //正在执行中的状态
    private boolean isRunning;
    private List<Circle> mCircles = new ArrayList<>();
    //每隔speedTime创建圆
    private long speedTime = 200;
    //上个圆创建的时间
    private long mLastCreateTime;
    private Rect bounds = new Rect();
    private Interpolator mInterpolator;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        attributes.recycle();
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        LogUtil.e("modeWidth--->" + widthMode + "--sizeWidth--->"  + widthSize);
        LogUtil.e("modeHeight--->" + heightMode + "--sizeHeight--->"  + heightSize);
        int width;
        int height;
        if(widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min((int) maxWaveRadius * 2, widthSize);
        } else {
            width = (int) (maxWaveRadius * 2);
        }

        if(heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min((int) maxWaveRadius * 2, heightSize);
        } else {
            height = (int) maxWaveRadius * 2;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (!isSetMaxRadius) {
            maxWaveRadius = Math.min(w, h) / 2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getDrawingRect(bounds);
        Iterator<Circle> iterator = mCircles.iterator();
        while (iterator.hasNext()) {
            Circle circle = iterator.next();
            //超过duration时间的圆移除掉
            if (System.currentTimeMillis() - circle.getCreateTime() < mDuration) {
                paint.setAlpha(circle.getAlpha());
                int width = getWidth() / 2;
                int centerX = bounds.centerX();
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, circle.getWaveRadius(), paint);
            } else {
                iterator.remove();
            }
        }
        if (mCircles.size() > 0) {
            postInvalidateDelayed(10);
        }
    }


    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case CREATE_CIRCLE :
                    removeMessages(CREATE_CIRCLE);
                    if(isRunning) {
                        createCircle();
                        sendEmptyMessageDelayed(CREATE_CIRCLE, speedTime);
                    }
                    break;
            }
        }
    };

    private void createCircle() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastCreateTime < speedTime) {
            return;
        }
        Circle circle = new Circle();
        mCircles.add(circle);
        invalidate();
        //更新最后创建时间
        mLastCreateTime = currentTime;
    }

    /**
     * 启动
     */
    public void start() {
        if (!isRunning) {
            isRunning = true;
            handler.sendEmptyMessage(CREATE_CIRCLE);
        }
    }

    /**
     * 暂停
     */
    public void pause() {
        isRunning = false;
    }

    /**
     * 销毁
     */
    public void destroy(){
        isRunning = false;
        if(handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 设置一个圆存在的时间
     * @param duration  long时间
     */
    public void setDuration(long duration) {
        mDuration = duration;
    }

    /**
     * 设置初始的圆半径
     * @param minWaveRadius 半径
     */
    public void setMinWaveRadius(float minWaveRadius) {
        this.minWaveRadius = minWaveRadius;
    }

    /**
     * 设置最大半径
     * @param maxWaveRadius 半径
     */
    public void setMaxWaveRadius(float maxWaveRadius) {
        this.maxWaveRadius = maxWaveRadius;
        isSetMaxRadius = true;
    }

    /**
     * 设置圆的填充颜色
     * @param color int
     */
    public void setColor(int color) {
        paint.setColor(ContextCompactUtils.getColor(getContext(), color));
    }

    /**
     * 设置插值器
     */
    public void setInterpolator(Interpolator interpolator){
        mInterpolator = interpolator;
        if(interpolator == null) {
            mInterpolator = new LinearInterpolator();
        }
    }

    /**
     * paint style
     */
    public void setStyle(Paint.Style style){
        paint.setStyle(style);
    }

    private class Circle {
        //圆创建的时间
        private long mCreateTime;

        public Circle() {
            mCreateTime = System.currentTimeMillis();
        }

        //圆的透明度
        public int getAlpha() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return (int) ((1.0f - mInterpolator.getInterpolation(percent)) * 255);
        }

        //圆变化的半径
        public float getWaveRadius() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return minWaveRadius + mInterpolator.getInterpolation(percent) * (maxWaveRadius - minWaveRadius);
        }

        public long getCreateTime() {
            return mCreateTime;
        }
    }
}
