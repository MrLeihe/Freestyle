package com.sd.style.module;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sd.style.R;
import com.sd.style.common.base.BaseActivity;
import com.sd.style.common.base.BasePresenter;
import com.sd.style.module.home.v.fragment.HomeFragment;
import com.sd.style.module.mine.v.fragment.MineFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.main_container)
    FrameLayout main_container;
    @BindView(R.id.ll_switch_home)
    LinearLayout ll_switch_home;
    @BindView(R.id.iv_home)
    ImageView iv_home;
    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.ll_switch_income)
    LinearLayout ll_switch_income;
    @BindView(R.id.iv_income)
    ImageView iv_income;
    @BindView(R.id.tv_income)
    TextView tv_income;
    @BindView(R.id.rl_switch_run)
    RelativeLayout rl_switch_run;
    @BindView(R.id.iv_run_stroke)
    ImageView iv_run_stroke;
    @BindView(R.id.iv_run)
    ImageView iv_run;
    @BindView(R.id.ll_switch_market)
    LinearLayout ll_switch_market;
    @BindView(R.id.iv_market)
    ImageView iv_market;
    @BindView(R.id.tv_market)
    TextView tv_market;
    @BindView(R.id.ll_switch_mine)
    LinearLayout ll_switch_mine;
    @BindView(R.id.iv_mine)
    ImageView iv_mine;
    @BindView(R.id.tv_mine)
    TextView tv_mine;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ll_switch_home.setOnClickListener(this);
        ll_switch_income.setOnClickListener(this);
        ll_switch_market.setOnClickListener(this);
        ll_switch_mine.setOnClickListener(this);
        rl_switch_run.setOnClickListener(this);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private HomeFragment homeFragment;
    private MineFragment mineFragment;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_switch_home:
                switchHome();
                break;
            case R.id.ll_switch_income:

                break;
            case R.id.ll_switch_market:

                break;
            case R.id.ll_switch_mine:
                switchMine();
                break;
            case R.id.rl_switch_run:

                break;
        }
    }

    private void switchMine() {
        if(mineFragment == null) {
            mineFragment = MineFragment.newInstance();
        }
        switchFragment(R.id.main_container, currentFragment, mineFragment);
    }

    private void switchHome() {
        if(homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
        }
        switchFragment(R.id.main_container, currentFragment, homeFragment);
    }
}
