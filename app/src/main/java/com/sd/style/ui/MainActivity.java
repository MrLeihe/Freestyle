package com.sd.style.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;

import com.sd.style.R;
import com.sd.style.common.base.BaseActivity;
import com.sd.style.common.base.BasePresenter;
import com.sd.style.common.service.ITestBinder;
import com.sd.style.common.service.MyService;
import com.sd.style.common.widget.MapProgressView;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_assets)
    ImageView iv_assets;
    @BindView(R.id.btn_start)
    Button btn_start;
    @BindView(R.id.btn_invoke)
    Button btn_invoke;
    @BindView(R.id.map_progressbar)
    MapProgressView map_progressbar;

    private ITestBinder mBinder;
    private connection mConnection;
    private Intent mIntent;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btn_start.setOnClickListener((v) -> {
            start();
        });

        btn_invoke.setOnClickListener((v) -> {
            invoke();
        });
    }

    private void invoke() {
        if(mBinder == null) {
            throw new RuntimeException("the binder is null, please bindService first!");
        }
        mBinder.invokeBinderMethod();
    }

    private void start(){
        mIntent = new Intent(MainActivity.this, MyService.class);
        mConnection = new connection();
        //bindService(intent, mConnection, BIND_AUTO_CREATE);
        startService(mIntent);
    }

    private class connection implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (ITestBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void bindData() {
        //mergeSort();
        //selectSort();
        //bubbleSort();
        for (int i = 0; i <= 100; i++) {
            Message message = new Message();
            message.arg1 = i;
            handler.sendMessageDelayed(message, 1000);
        }
    }

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            int progress = msg.arg1;
//            map_progressbar.updateProgress(progress);
        }
    };

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unbindService(mConnection);
        stopService(mIntent);
    }
}
