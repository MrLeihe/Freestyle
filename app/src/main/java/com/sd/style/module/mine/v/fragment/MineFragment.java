package com.sd.style.module.mine.v.fragment;

import android.os.Handler;
import android.os.Message;

import com.sd.style.R;
import com.sd.style.common.base.BaseFragment;
import com.sd.style.common.base.BasePresenter;
import com.sd.style.common.widget.SaleProgressView;

import butterknife.BindView;

/**
 * Author: HeLei on 2017/10/24 00:23
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.sale_view)
    SaleProgressView sale_view;

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
        handler.sendEmptyMessageDelayed(0, 100);
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

}
