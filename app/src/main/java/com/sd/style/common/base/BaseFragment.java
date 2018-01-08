package com.sd.style.common.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sd.style.R;
import com.sd.style.common.uitls.StatusBarUtils;

/**
 * @author: Rae.Ho
 * @description:
 * @date 2017/8/1  16:14
 */

public abstract class BaseFragment extends Fragment implements BaseView{

    protected Context mContext;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(mContext).inflate(getLayoutId(), container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        bindData();
        initPresenter();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void bindData();

    protected abstract BasePresenter initPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    /**
     * 重新设置标题栏高度
     */
    protected void changeTitleHeight() {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.base_toolbar);
        if (toolbar == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
            layoutParams.height = StatusBarUtils.getViewHeight(toolbar) + StatusBarUtils.getStatusHeight(getResources());
            toolbar.setPadding(toolbar.getPaddingLeft(),
                    StatusBarUtils.getStatusHeight(getResources()) + toolbar.getPaddingTop(),
                    toolbar.getPaddingRight(),
                    toolbar.getPaddingBottom());
            toolbar.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
