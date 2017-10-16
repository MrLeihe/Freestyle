package com.sd.style.module;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.sd.style.R;
import com.sd.style.common.base.BaseActivity;
import com.sd.style.common.base.BasePresenter;

public class TestActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindData() {
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}
