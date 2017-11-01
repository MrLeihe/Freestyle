package com.sd.style.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sd.style.R;
import com.sd.style.common.uitls.ContextCompactUtils;

/**
 * Author: HeLei on 2017/10/27 15:05
 */

public class PathView extends View {

    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Path path;
    private Paint paint;

    private void init() {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompactUtils.getColor(getContext(), R.color.theme_color));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(300, 300);
        path.lineTo(100, 500);
        path.lineTo(500, 500);
        path.close();
        canvas.drawPath(path, paint);
    }
}
