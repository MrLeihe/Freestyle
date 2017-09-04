package com.sd.style.ui;

import com.sd.style.R;
import com.sd.style.common.base.BaseActivity;
import com.sd.style.common.base.BasePresenter;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindData() {
        //mergeSort();
        //selectSort();
        //bubbleSort();
    }



    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    private void subscribe() {

    }
}
