package com.sd.style.module.home.v.fragment;

import com.orhanobut.logger.Logger;
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
        double x = 5.0 / 13;
        double asin = Math.asin(x);
        Logger.e("x-----" + x + "---asin----------" + asin);
        double degrees = Math.toDegrees(asin);
        Logger.e("degrees--------" + degrees);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
