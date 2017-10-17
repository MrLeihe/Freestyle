package com.sd.style.module.home.v.fragment;

import com.sd.style.R;
import com.sd.style.common.base.BaseFragment;
import com.sd.style.common.base.BasePresenter;
import com.sd.style.common.widget.InsideCircle;

import butterknife.BindView;

/**
 * Author: HeLei on 2017/10/16 18:25
 */

public class HomeFragment extends BaseFragment{

    @BindView(R.id.inside_circle)
    InsideCircle inside_circle;

    public static HomeFragment newInstance(){
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
        inside_circle.setProgress(0, 80);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
