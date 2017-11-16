package com.sd.style.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.sd.style.common.uitls.ToastUtils;
import com.sd.style.module.MainActivity;

/**
 * Author: HeLei on 2017/11/15 16:56
 */

public class BootReceiver extends BroadcastReceiver {

    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(TextUtils.equals(intent.getAction(), ACTION)) {
            MainActivity.Companion.show(context);
            Logger.e("正在开机...");
            ToastUtils.showLongToast("监听到开机广播");
        }
    }
}
