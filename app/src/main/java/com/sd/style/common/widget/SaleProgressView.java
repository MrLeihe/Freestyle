package com.sd.style.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sd.style.R;
import com.sd.style.common.uitls.ContextCompactUtils;
import com.sd.style.common.uitls.UIUtils;

/**
 * Author: HeLei on 2017/10/24 10:18
 * 自定义的进度条view
 */

public class SaleProgressView extends View {

    //边框的宽度
    private float sideWidth = UIUtils.dp2px(1);
    //测量得到的宽度
    private int measuredWidth;
    //测量得到的高度
    private int measuredHeight;
    //圆角半径
    private float radius;
    //边框画笔
    private Paint sidePaint;
    //背景进度画笔
    private Paint progressPaint;
    //文字画笔
    private Paint textPaint;
    //背景bitmap
    private Bitmap bitmapBg;
    //进度bitmap
    private Bitmap bitmapFg;
    private Rect bounds = new Rect();
    private RectF rectF = new RectF();
    private RectF fgRectF = new RectF();
    private Rect textBounds = new Rect();
    private PorterDuffXfermode porterDuffXfermode;
    //当前进度百分比
    private float scale;

    public SaleProgressView(Context context) {
        super(context);
        initConfig();
    }

    public SaleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initConfig();
    }

    public SaleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig();
    }

    private void initConfig() {
        sidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sidePaint.setStrokeWidth(sideWidth);
        sidePaint.setStyle(Paint.Style.STROKE);
        sidePaint.setColor(ContextCompactUtils.getColor(getContext(), R.color.theme_color));

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(UIUtils.dp2px(14));
        textPaint.setColor(ContextCompactUtils.getColor(getContext(), R.color.theme_color));

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        bitmapBg = BitmapFactory.decodeResource(getResources(), R.drawable.bg, options);
        bitmapFg = BitmapFactory.decodeResource(getResources(), R.drawable.fg, options);

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        radius = measuredWidth / 2.0f;
        rectF.set(sideWidth, sideWidth, measuredWidth - sideWidth, measuredHeight - sideWidth);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getDrawingRect(bounds);
        //绘制进度背景
        drawBgBitmap(canvas);
        //绘制进度条
        drawFgBitmap(canvas, scale);
        //绘制边框
        canvas.drawRoundRect(rectF, radius, radius, sidePaint);
        //绘制文字
        drawProgressText(canvas);
    }

    private Bitmap bgBitmap;

    private void drawBgBitmap(Canvas canvas) {
        if (bgBitmap == null) {
            bgBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        }
        //新建画布
        Canvas bgCanvas = new Canvas(bgBitmap);
        bgCanvas.drawRoundRect(rectF, radius, radius, progressPaint);
        progressPaint.setXfermode(porterDuffXfermode);
        bgCanvas.drawBitmap(bitmapBg, null, rectF, progressPaint);
        //将bitmap绘制在当前画布上
        canvas.drawBitmap(bgBitmap, 0, 0, null);
        progressPaint.setXfermode(null);
    }

    private Bitmap fgBitmap;

    private void drawFgBitmap(Canvas canvas, float scale) {
        if (scale <= 0) {
            return;
        }
        if (scale > 1) {
            scale = 1;
        }
        if (fgBitmap == null) {
            fgBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        }
        //新建画布
        Canvas fgCanvas = new Canvas(fgBitmap);
        fgRectF.set(sideWidth, sideWidth, (measuredWidth - sideWidth) * scale, measuredHeight - sideWidth);
        fgCanvas.drawRoundRect(fgRectF, radius, radius, progressPaint);
        progressPaint.setXfermode(porterDuffXfermode);
        fgCanvas.drawBitmap(bitmapFg, null, rectF, progressPaint);
        //将bitmap绘制在当前画布上
        canvas.drawBitmap(fgBitmap, 0, 0, null);
        progressPaint.setXfermode(null);
    }

    private void drawProgressText(Canvas canvas) {
        Bitmap textBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        Canvas textCanvas = new Canvas(textBitmap);
        String progressText = (int) (scale * 100) + "%";
        textPaint.setColor(ContextCompactUtils.getColor(getContext(), R.color.theme_color));
        textPaint.getTextBounds(progressText, 0, progressText.length(), textBounds);
        textCanvas.drawText(progressText, bounds.centerX() - textBounds.width() / 2, bounds.centerY() + textBounds.height() / 2, textPaint);
        textPaint.setXfermode(porterDuffXfermode);
        textPaint.setColor(ContextCompactUtils.getColor(getContext(), R.color.white));
        textCanvas.drawRoundRect(new RectF(sideWidth, sideWidth, (measuredWidth - sideWidth) * scale, measuredHeight - sideWidth), radius, radius, textPaint);
        canvas.drawBitmap(textBitmap, 0, 0, null);
        textPaint.setXfermode(null);
    }

    public void setScale(float scale) {
        this.scale = scale / 100;
        invalidate();
    }
}
