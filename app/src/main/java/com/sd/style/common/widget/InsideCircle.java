package com.sd.style.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.sd.style.R;
import com.sd.style.common.uitls.UIUtils;


/**
 * Created by Administrator on 2016/12/20.
 */
public class InsideCircle extends View {

    public InsideCircle(Context context) {
        super(context);
        init();
    }

    public InsideCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InsideCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //将dip转为px
    public int dip2px(float dip) {
        //获取屏幕密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    private Paint strokePaint;//进度条画笔
    private Paint textPaint;//绘制文字的画笔
    private Paint circlePaint;//绘制圆点的画笔
    private int strokeWidth = dip2px(10);//进度条宽度
    private float total;//总进度
    private float progress;//当前进度

    public double getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    private void init() {
        strokePaint = new Paint();
        strokePaint.setAntiAlias(true);//抗锯齿，会消耗较大资源
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);
        strokePaint.setStrokeJoin(Paint.Join.ROUND);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.WHITE);
        textPaint.setDither(true);

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.WHITE);
    }

    private Rect bounds = new Rect();
    private RectF mArcRect = new RectF();
    private int imageWidth = 80;
    @Override
    protected void onDraw(Canvas canvas) {
        //获取绘制区域
        getDrawingRect(bounds);
        int size = bounds.height() > bounds.width() ? bounds.width() : bounds.height();
//        //计算外部圆半径
        float outerRadius = (size - strokeWidth) / 2f;
        int padding = (imageWidth - strokeWidth) / 2;
//        //中心圆直径
        float centerRadius = outerRadius - strokeWidth / 2f-padding/2;
//        float horizontalPadding = strokeWidth + (bounds.width() - size) / 2f;
//        float verticalPadding = strokeWidth + (bounds.height() - size) / 2f;
        float horizontalPadding = padding + strokeWidth / 2 + (bounds.width() - size) / 2f;
        float verticalPadding = padding + strokeWidth / 2 + (bounds.height() - size) / 2f;
//        Log.e("TAG", "left:" + bounds.left + ",top:" + bounds.top + ",right:" + bounds.right + ",bottom:" + bounds.bottom);
        mArcRect.set(bounds.left + horizontalPadding, bounds.top + verticalPadding,
                bounds.right - horizontalPadding, bounds.bottom - verticalPadding);
        //绘制进度条背景
        strokePaint.setColor(Color.parseColor("#55ffffff"));
        canvas.drawArc(mArcRect, 165, 210, false, strokePaint);

        //绘制进度条
        //计算进度条百分比
        float proportion = 0;
        if (total > 0) {
            proportion = progress / total;
        }
        if (proportion > 0) {
            strokePaint.setColor(Color.parseColor("#ffffff"));
            canvas.drawArc(mArcRect, 130, 280 * proportion, false, strokePaint);
            //在终点绘制圆
            float angle = 280 * proportion - 140;
            float circleX = (float) (centerRadius * Math.sin(Math.toRadians(angle)) + bounds.width() / 2);
            float circleY = (float) (bounds.height() / 2 - centerRadius * Math.cos(Math.toRadians(angle)));
            canvas.drawCircle(circleX, circleY, strokeWidth, circlePaint);
        }


        //绘制底部文字
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(dip2px(13));
        String content = getContext().getResources().getString(R.string.total) + String.format("%.2f", total) + " " + getContext().getResources().getString(R.string.yuan);
        //计算字的宽和高
        Rect rect = new Rect();
        textPaint.getTextBounds(content, 0, content.length(), rect);
        float textX = bounds.centerX() - rect.width() / 2;
        float textY = (float) (bounds.centerY() + centerRadius * Math.cos(Math.toRadians(40)) + rect.height() / 2);
        canvas.drawText(content, textX, textY, textPaint);

        //绘制价格
        textPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DINCond-Black.otf"));
        textPaint.setTextSize(dip2px(72));
        String number = String.format("%.2f", progress);
        Rect numberRect = new Rect();
        textPaint.getTextBounds(number, 0, number.length(), numberRect);
        canvas.drawText(number, bounds.centerX() - numberRect.width() / 2, bounds.centerY() + numberRect.height() / 2, textPaint);

        //绘制顶部的高
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(dip2px(15));
        String topContent = getContext().getResources().getString(R.string.current);
        Rect topRect = new Rect();
        textPaint.getTextBounds(topContent, 0, topContent.length(), topRect);
        float toptextX = bounds.centerX() - topRect.width() / 2;
        float toptextY = (float) (bounds.centerY() - UIUtils.dp2px(36)- UIUtils.dp2px(5));
        canvas.drawText(topContent, toptextX, toptextY, textPaint);
    }
}
