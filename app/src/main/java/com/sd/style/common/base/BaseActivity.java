package com.sd.style.common.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.sd.style.GlobalConstant;
import com.sd.style.R;

/**
 * description: activity 基类
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initScreenConfig();
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

    protected Fragment preFragment;
    protected Fragment currentFragment;

    /**
     * 切换fragment
     * @param resId 承载fragment的布局id
     * @param from  前一个fragment
     * @param to    将要显示的fragment
     */
    protected void switchFragment(int resId, Fragment from, Fragment to) {
        if (from == to) {
            return;
        }
        preFragment = from;
        currentFragment = to;
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.setCustomAnimations(R.anim.fade_in, R.anim.slide_out);
        if (from == null) {
            Logger.e("switchFragment------>from===null");
            if (!to.isAdded()) {
                fm.add(resId, to).commitAllowingStateLoss();
            } else {
                fm.show(to).commitAllowingStateLoss();
            }
        } else {
            Logger.e("switchFragment------>from!=null");
            if (!to.isAdded()) {
                fm.hide(from).add(resId, to).commitAllowingStateLoss();
            } else {
                fm.hide(from).show(to).commitAllowingStateLoss();
            }
        }
    }

    /**
     * 沉浸式状态栏
     */
    protected void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
