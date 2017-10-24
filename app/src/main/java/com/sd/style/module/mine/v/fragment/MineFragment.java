package com.sd.style.module.mine.v.fragment;

import com.sd.style.R;
import com.sd.style.common.base.BaseFragment;
import com.sd.style.common.base.BasePresenter;

/**
 * Author: HeLei on 2017/10/24 00:23
 */

public class MineFragment extends BaseFragment{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
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

    public static MineFragment newInstance() {
        return new MineFragment();
    }
}
