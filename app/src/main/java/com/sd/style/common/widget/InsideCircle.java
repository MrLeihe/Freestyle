package com.sd.style.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.sd.style.R;
import com.sd.style.common.uitls.UIUtils;
import com.sd.style.common.uitls.Utils;


/**
 * Created by Administrator on 2016/12/20.
 */
public class InsideCircle extends View {

    public static final int Start_Degree = 155;
    public static final int Total_Degree = 230;
    private Bitmap mCarBitmap;

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
    private Paint bitmapPaint;//绘制汽车的画笔
    private Paint kmPaint;
    private int strokeWidth = dip2px(10);//进度条宽度
    private float total;//总进度
    private float progress;//当前进度
    private int minDistance;//最小距离
    private int maxDistance;//最大距离

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

    public void setMinDistance(int minDistance) {
        this.minDistance = minDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
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

        bitmapPaint = new Paint();
        bitmapPaint.setStyle(Paint.Style.FILL);
        bitmapPaint.setFilterBitmap(true);
        bitmapPaint.setDither(true);
        mCarBitmap = BitmapFactory.decodeResource(Utils.getContext().getResources(), R.mipmap.car);

        kmPaint = new Paint();
        kmPaint.setAntiAlias(true);
        kmPaint.setStyle(Paint.Style.FILL);
        kmPaint.setColor(Color.WHITE);
    }

    private Rect bounds = new Rect();
    private RectF mArcRect = new RectF();
    private int imageWidth = 80;

    @Override
    protected void onDraw(Canvas canvas) {
        //获取绘制区域
        getDrawingRect(bounds);
        Logger.e("width------" + bounds.width() + "---height-----" + bounds.height());
        int size = bounds.height() > bounds.width() ? bounds.width() : bounds.height();
        //计算外部圆半径
        float outerRadius = (size - strokeWidth) / 2f;
        int padding = (imageWidth - strokeWidth) / 2;
        //中心圆直径
        float centerRadius = outerRadius - strokeWidth / 2f - padding / 2;
        float horizontalPadding = padding + strokeWidth / 2 + (bounds.width() - size) / 2f;
        float verticalPadding = padding + strokeWidth / 2 + (bounds.height() - size) / 2f;
        mArcRect.set(bounds.left + horizontalPadding, bounds.top + verticalPadding,
                bounds.right - horizontalPadding, bounds.bottom - verticalPadding);
        //绘制进度条背景
        strokePaint.setColor(Color.parseColor("#55ffffff"));
        canvas.drawArc(mArcRect, Start_Degree, Total_Degree, false, strokePaint);

        //绘制进度条
        //计算进度条百分比
        float proportion = 0;
        if (total > 0) {
            proportion = progress / total;
        }
        //130,280
        if (proportion > 0) {
            strokePaint.setColor(Color.parseColor("#ffffff"));
            canvas.drawArc(mArcRect, Start_Degree, Total_Degree * proportion, false, strokePaint);
            //在终点绘制圆
            float angle = 280 * proportion - 140;
            float circleX = (float) (centerRadius * Math.sin(Math.toRadians(angle)) + bounds.width() / 2);
            float circleY = (float) (bounds.height() / 2 - centerRadius * Math.cos(Math.toRadians(angle)));
            canvas.drawCircle(circleX, circleY, strokeWidth, circlePaint);
        }

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
        float toptextY = (float) (bounds.centerY() - UIUtils.dp2px(36) - UIUtils.dp2px(5));
        canvas.drawText(topContent, toptextX, toptextY, textPaint);

        //画车
        float carX = bounds.centerX() - mCarBitmap.getWidth() / 2;
        float carY = (float) (bounds.centerY() + centerRadius * Math.sin(Math.toRadians(25)));
        canvas.drawBitmap(mCarBitmap, carX, carY, bitmapPaint);

        //画底部距离文字
        kmPaint.setTextSize(dip2px(8));
        String minKmContent = minDistance + " km";
        Rect distanceRect = new Rect();
        kmPaint.getTextBounds(minKmContent, 0, minKmContent.length(), distanceRect);
        float minKmX = (float) (bounds.centerX() - centerRadius * Math.cos(Math.toRadians(25)) - distanceRect.width() / 2 + strokeWidth / 2);
        float minKmY = (float) (bounds.centerY() + centerRadius * Math.sin(Math.toRadians(25)) + distanceRect.height() * 2.5);
        canvas.drawText(minKmContent, minKmX, minKmY, kmPaint);

        maxDistance = 500;
        String maxKmContent = maxDistance + " km";
        kmPaint.getTextBounds(maxKmContent, 0, maxKmContent.length(), distanceRect);
        float maxKmX = (float) (bounds.centerX() + centerRadius * Math.cos(Math.toRadians(25)) - distanceRect.width() / 2 - strokeWidth / 2);
        float maxKmY = (float) (bounds.centerY() + centerRadius * Math.sin(Math.toRadians(25)) + distanceRect.height() * 2.5);
        canvas.drawText(maxKmContent, maxKmX, maxKmY, kmPaint);
    }

}
