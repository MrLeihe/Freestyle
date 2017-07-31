package com.sd.style.common.app;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;

/**
 * @author: SanShi
 * @description: application基类
 * @date 2017/7/31  18:57
 */

public class BaseApplication extends MultiDexApplication{

    private static BaseApplication instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getInstance(){
        return instance;
    }

    /**
     * 重启app
     */
    public void restartApp(){
        Intent intentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intentForPackage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentForPackage);
    }
}
