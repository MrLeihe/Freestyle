package com.sd.style.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sd.style.R;


/**
 * Author: HeLei on 2017/9/29 17:52
 */

public class MapProgressView extends LinearLayout {

    private Context mContext;
    private int mScreenHeight;
    private int splitNum = 5;
    private String[] income = {"¥40", "¥30","¥20","¥10"};
    private String[] kilometer = {"400km", "300km","200km","100km"};
    private int[] lineIds = {R.id.TAG1001, R.id.TAG1002, R.id.TAG1003, R.id.TAG1004};

    public MapProgressView(Context context) {
        super(context);
        init(context);
    }

    public MapProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MapProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        this.mContext = context;
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        mScreenHeight = dm.heightPixels;
        setupView();
    }


    private ProgressBar mRun_progressbar;
    private RelativeLayout mRl_scale;

    private void setupView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.map_progress, null);
        mRun_progressbar = (ProgressBar) view.findViewById(R.id.run_progressbar);
        mRl_scale = (RelativeLayout) view.findViewById(R.id.rl_scale);
        setDefaultBar();
        createLineAndText();
        addView(view);
    }

    private int viewHeight;
    private int viewWidth;
    private int lineWidth;
    private int lineHeight;
    private int distance;
    private int textMargin;

    private void setDefaultBar() {
        Logger.e("height------" + mScreenHeight);
        viewWidth = dp2px(30f);
        viewHeight = (int) (mScreenHeight * 0.6);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(viewWidth, viewHeight);
        mRun_progressbar.setLayoutParams(lp);
        mRl_scale.setLayoutParams(lp);

        initParams();
    }

    private void initParams() {
        lineWidth = viewWidth /2;
        lineHeight = dp2px(1f);
        distance = viewHeight / splitNum;
        textMargin = dp2px(2);
    }

    private void createLineAndText() {
        mRl_scale.removeAllViews();
        for (int i = 0; i < income.length ; i++) {
            createDetail(lineIds[i], income[i], kilometer[i], (i + 1) * distance);
        }
    }

    private void createDetail(int viewId, String incomeStr, String kmStr, int lineDistance) {
        //line
        View line1 = new View(mContext);
        line1.setBackgroundColor(Color.parseColor("#979797"));
        RelativeLayout.LayoutParams lpLine = new RelativeLayout.LayoutParams(lineWidth, lineHeight);
        lpLine.topMargin = lineDistance;
        lpLine.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        line1.setId(viewId);
        line1.setLayoutParams(lpLine);

        //textView top
        TextView textViewTop = new TextView(mContext);
        textViewTop.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        textViewTop.setTextColor(Color.parseColor("#979797"));
        textViewTop.getPaint().setFakeBoldText(true);
        textViewTop.setText(incomeStr);
        RelativeLayout.LayoutParams lpTextTop = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpTextTop.addRule(RelativeLayout.BELOW, line1.getId());
        lpTextTop.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lpTextTop.topMargin = -dp2px(18);
        textViewTop.setLayoutParams(lpTextTop);
        //textView bottom
        TextView textViewBottom = new TextView(mContext);
        textViewBottom.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        textViewBottom.setTextColor(Color.parseColor("#979797"));
        textViewBottom.setText(kmStr);
        RelativeLayout.LayoutParams lpTextBottom = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpTextBottom.addRule(RelativeLayout.BELOW, line1.getId());
        lpTextBottom.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lpTextBottom.topMargin = textMargin;
        textViewBottom.setLayoutParams(lpTextBottom);

        mRl_scale.addView(line1);
        mRl_scale.addView(textViewTop);
        mRl_scale.addView(textViewBottom);
    }

    /**
     * 更新进度条
     * @param progress  当前进度
     */
    public void updateProgress(int progress){
        mRun_progressbar.setProgress(progress);
    }

    public void resetProgressSize(int width, int height){
        viewWidth = width;
        viewHeight = height;
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
        mRun_progressbar.setLayoutParams(lp);
        mRl_scale.setLayoutParams(lp);
        initParams();
        createLineAndText();
    }

    public MapProgressView setKilometer(String[] newKilometer){
        this.kilometer = newKilometer;
        splitNum = newKilometer.length +1;
        return this;
    }

    public MapProgressView setIncome(String[] newIncome){
        this.income = newIncome;
        splitNum = newIncome.length +1;
        return this;
    }

    public MapProgressView setViewIds(int[] newViewIds){
        this.lineIds = newViewIds;
        splitNum = newViewIds.length +1;
        return this;
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public int px2dp( float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
