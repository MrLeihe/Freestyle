package com.sd.style.module.home.v.fragment;

import android.widget.Button;

import com.sd.style.R;
import com.sd.style.common.base.BaseFragment;
import com.sd.style.common.base.BasePresenter;
import com.sd.style.common.widget.HomeCircleView;
import com.sd.style.module.TestActivity;

import butterknife.BindView;

/**
 * Author: HeLei on 2017/10/16 18:25
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.inside_circle)
    HomeCircleView inside_circle;
    @BindView(R.id.finger_test)
    Button finger_test;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindData() {
        inside_circle.setMinDistance(0);
        inside_circle.setMaxDistance(500);
        inside_circle.setProgress(80);
        inside_circle.setTotal(100);
        inside_circle.start();

        finger_test.setOnClickListener((v) ->
            TestActivity.showActivity(mContext)
        );
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
