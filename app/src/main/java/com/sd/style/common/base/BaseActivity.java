package com.sd.style.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.sd.style.GlobalConstant;

import butterknife.ButterKnife;

/**
 * description: activity 基类
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initScreenConfig();
        initConfig();
        initView();
        bindData();
        initPresenter();
    }

    private void initScreenConfig() {
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        Logger.e("base---height:" + height);
        GlobalConstant.screen.screenWidth = width;
        GlobalConstant.screen.screenHeight = height;
    }

    private void initConfig() {
        ButterKnife.bind(this);
    }

    /**
     * @return layout布局id
     */
    protected abstract int getLayoutId();

    /**
     * find view
     */
    protected abstract void initView();

    /**
     * <p>绑定数据</p>
     */
    protected abstract void bindData();

    /**
     * <p>初始化presenter</p>
     */
    protected abstract BasePresenter initPresenter();

    protected void showToast(int toastId) {
        showToast(toastId, Toast.LENGTH_SHORT);
    }

    protected void showToast(int toastId, int duration) {
        showToast(getString(toastId), duration);
    }

    protected void showToast(String toastContent) {
        showToast(toastContent, Toast.LENGTH_SHORT);
    }

    protected void showToast(String toastContent, int duration) {
        if (TextUtils.isEmpty(toastContent)) {
            return;
        }
        Toast.makeText(this, toastContent, duration).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void hideError() {

    }
}
