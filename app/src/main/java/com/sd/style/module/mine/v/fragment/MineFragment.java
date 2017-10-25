package com.sd.style.module.mine.v.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;

import com.sd.style.R;
import com.sd.style.common.base.BaseFragment;
import com.sd.style.common.base.BasePresenter;
import com.sd.style.common.uitls.UIUtils;
import com.sd.style.common.widget.SaleProgressView;
import com.sd.style.common.widget.WaveView;

import butterknife.BindView;

/**
 * Author: HeLei on 2017/10/24 00:23
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.sale_view)
    SaleProgressView sale_view;
    @BindView(R.id.wave_view)
    WaveView wave_view;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindData() {
        handler.sendEmptyMessage(0);
        wave_view.setDuration(2000);
        wave_view.setMinWaveRadius(UIUtils.dp2px(1));
        wave_view.setMaxWaveRadius(UIUtils.dp2px(50));
        wave_view.setInterpolator(new LinearOutSlowInInterpolator());
        wave_view.setColor(R.color.theme_color);
        wave_view.start();
    }

    private int progress;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            progress++;
            sale_view.setScale(progress);
            handler.removeMessages(0);
            if(progress < 100) {
                handler.sendEmptyMessageDelayed(0, 100);
            }
        }
    };

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        wave_view.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        wave_view.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wave_view.destroy();
    }
}
