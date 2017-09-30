package com.sd.style.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.sd.style.common.uitls.ToastUtils;

/**
 * Author: HeLei on 2017/9/18 00:22
 */

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e("onCreate-----");
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.e("onBind-----");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.e("onUnbind-----");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e("onDestroy-----");
    }

    private void binderMethod(){
        ToastUtils.showShortToast("服务里的方法执行了");
    }

    private class MyBinder extends Binder implements ITestBinder{

        @Override
        public void invokeBinderMethod() {
            binderMethod();
        }
    }
}
