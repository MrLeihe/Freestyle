package com.sd.style.common.app;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.sd.style.BuildConfig;
import com.sd.style.GlobalConstant;

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

    public static Context getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initConfig();
    }

    private void initConfig() {
        //初始化日志logger
        loggerStrategy();
    }

    private void loggerStrategy() {
        FormatStrategy strategy = PrettyFormatStrategy.newBuilder()
                .tag(GlobalConstant.Log.LOG_TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(strategy){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.LOG_ON;
            }
        });
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
