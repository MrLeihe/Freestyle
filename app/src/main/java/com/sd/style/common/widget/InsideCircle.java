package com.sd.style.common.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.orhanobut.logger.Logger;
import com.sd.style.R;
import com.sd.style.common.uitls.UIUtils;
import com.sd.style.common.uitls.Utils;

import static com.sd.style.R.string.current;


/**
 * Created by Administrator on 2016/12/20.
 */
public class InsideCircle extends View {

    public static final int Start_Degree = 155;
    public static final int Total_Degree = 230;
    public float Base_Degree;
    public static final String Normal_Color = "#55ffffff";
    public static final String Highlight_Color = "#ffffff";
    //小圆点半径
    public static final int Small_Point_Radius = UIUtils.dp2px(1);
    //大圆点半径
    public static final int Large_Point_Radius = UIUtils.dp2px(4);
    //默认分成25等分
    public int part = 25;
    //平均夹角度数
    public double pointDegree = Total_Degree / part;

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
    private Bitmap mCarBitmap;
    private Bitmap fingerBitmap;
    private PointF point;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress, float total) {
        this.progress = progress;
        this.total = total;
        setProgressBgAnimation();
        requestLayout();
    }

    public void setMinDistance(int minDistance) {
        this.minDistance = minDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    private void init() {
        Base_Degree = (float) (90 - Math.toDegrees(5.0 / 13));
        Logger.e("baseDegree------------" + Base_Degree);
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
        fingerBitmap = BitmapFactory.decodeResource(Utils.getContext().getResources(), R.mipmap.home_progress_arrow);

        kmPaint = new Paint();
        kmPaint.setAntiAlias(true);
        kmPaint.setStyle(Paint.Style.FILL);
        kmPaint.setColor(Color.WHITE);

        //指针坐标
        point = new PointF();
    }

    private Rect bounds = new Rect();
    private RectF mArcRect = new RectF();
    private int imageWidth = 80;
    private float outerRadius;
    private float centerRadius;
    private float startDegree = 65;
    private float animationTotal = 5000;
    private float animationUpdate;

    @Override
    protected void onDraw(Canvas canvas) {
        //获取绘制区域
        getDrawingRect(bounds);
        Logger.e("width------" + bounds.width() + "---height-----" + bounds.height());
        int size = bounds.height() > bounds.width() ? bounds.width() : bounds.height();
        //计算外部圆半径
        outerRadius = (size - strokeWidth) / 2f;
        int padding = (imageWidth - strokeWidth) / 2;
        //中心圆直径
        centerRadius = outerRadius - strokeWidth / 2f - padding / 2;
        float horizontalPadding = padding + strokeWidth / 2 + (bounds.width() - size) / 2f;
        float verticalPadding = padding + strokeWidth / 2 + (bounds.height() - size) / 2f;
        mArcRect.set(bounds.left + horizontalPadding, bounds.top + verticalPadding,
                bounds.right - horizontalPadding, bounds.bottom - verticalPadding);
        //绘制进度条背景
        strokePaint.setColor(Color.parseColor(Normal_Color));
        canvas.drawArc(mArcRect, Start_Degree, Total_Degree * (animationUpdate / animationTotal), false, strokePaint);

        //绘制进度条
        //130,280
        if (currentPercent > 0) {
            strokePaint.setColor(Color.parseColor(Highlight_Color));
            canvas.drawArc(mArcRect, Start_Degree, Total_Degree * currentPercent, false, strokePaint);
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
        String topContent = getContext().getResources().getString(current);
        Rect topRect = new Rect();
        textPaint.getTextBounds(topContent, 0, topContent.length(), topRect);
        float toptextX = bounds.centerX() - topRect.width() / 2;
        float toptextY = (float) (bounds.centerY() - UIUtils.dp2px(36) - UIUtils.dp2px(5));
        canvas.drawText(topContent, toptextX, toptextY, textPaint);

        //画车
        float carX = bounds.centerX() - mCarBitmap.getWidth() / 2;
        float carY = (float) (bounds.centerY() + centerRadius * Math.sin(Math.toRadians(25)));
        canvas.drawBitmap(mCarBitmap, carX, carY, bitmapPaint);

        //画底部最小距离文字
        kmPaint.setTextSize(dip2px(8));
        String minKmContent = minDistance + " km";
        Rect distanceRect = new Rect();
        kmPaint.getTextBounds(minKmContent, 0, minKmContent.length(), distanceRect);
        float minKmX = (float) (bounds.centerX() - centerRadius * Math.cos(Math.toRadians(25)) - distanceRect.width() / 2 + strokeWidth / 2);
        float minKmY = (float) (bounds.centerY() + centerRadius * Math.sin(Math.toRadians(25)) + distanceRect.height() * 2.5);
        canvas.drawText(minKmContent, minKmX, minKmY, kmPaint);

        //画底部最大距离文字
        maxDistance = 500;
        String maxKmContent = maxDistance + " km";
        kmPaint.getTextBounds(maxKmContent, 0, maxKmContent.length(), distanceRect);
        float maxKmX = (float) (bounds.centerX() + centerRadius * Math.cos(Math.toRadians(25)) - distanceRect.width() / 2 - strokeWidth / 2);
        float maxKmY = (float) (bounds.centerY() + centerRadius * Math.sin(Math.toRadians(25)) + distanceRect.height() * 2.5);
        canvas.drawText(maxKmContent, maxKmX, maxKmY, kmPaint);

        //画圆点
        Logger.e("centerRadius------------" + centerRadius);
        for (float i = 0; i <= part; i++) {
            float pointX = (float) (bounds.centerX() - 0.9 * centerRadius * Math.sin(Math.toRadians(Base_Degree + i * pointDegree)));
            float pointY = (float) (bounds.centerY() + 0.9 * centerRadius * Math.cos(Math.toRadians(Base_Degree + i * pointDegree)));
            //整除5则为大圆点
            int radius = i % 5 == 0 ? Large_Point_Radius : Small_Point_Radius;
            float current = i / part;
            if (current > currentPercent || currentPercent == 0) {
                circlePaint.setColor(Color.parseColor(Normal_Color));
            } else {
                circlePaint.setColor(Color.parseColor(Highlight_Color));
            }
            canvas.drawCircle(pointX, pointY, radius, circlePaint);
        }

        //画指针图片
        //定义矩阵对象
        Matrix matrix = new Matrix();
        matrix.postRotate(startDegree + currentAngle + 180, bounds.centerX(), bounds.centerY());
        Bitmap bitmap = Bitmap.createBitmap(fingerBitmap, 0, 0, fingerBitmap.getWidth(),
                fingerBitmap.getHeight(), matrix, true);
        canvas.drawBitmap(bitmap, point.x, point.y, bitmapPaint);
    }

    private float currentAngle;
    private ValueAnimator valueAnimator;
    private ValueAnimator fingerValueAnimator;
    private float currentPercent;

    /**
     * 进度背景动画
     */
    private void setProgressBgAnimation() {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofFloat(0, animationTotal);
            valueAnimator.setDuration(1200);
            valueAnimator.setStartDelay(200);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    animationUpdate = (float) animation.getAnimatedValue();
                    invalidate();
                }


            });

            valueAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Logger.e("onAnimationEnd----------------------->");
                    if (total > 0) {
                        float current = progress / total;
                        if (current > 1) {
                            current = 1;
                        }
                        setFingerAnimation(0, current);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        if (!valueAnimator.isRunning()) {
            valueAnimator.start();
        }
    }

    /**
     * 开始动画
     *
     * @param start 初始值
     * @param end   最大进度
     */
    private void setFingerAnimation(float start, float end) {
        if (fingerValueAnimator == null) {
            fingerValueAnimator = ValueAnimator.ofFloat(start, end);
            fingerValueAnimator.setDuration(5000);
            fingerValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentPercent = (float) animation.getAnimatedValue();
                    currentAngle = currentPercent * Total_Degree;
                    //计算指针的坐标
                    Logger.e("currentAngle-----------" + currentAngle);
                    point.x = (float) (bounds.centerX() - outerRadius * Math.sin(Math.toRadians(startDegree + currentAngle)));
                    point.y = (float) (bounds.centerY() + outerRadius * Math.cos(Math.toRadians(startDegree + currentAngle)));
                    //进度重绘

                    invalidate();
                }
            });
        }
        if (!fingerValueAnimator.isRunning()) {
            fingerValueAnimator.start();
        }
    }

}
