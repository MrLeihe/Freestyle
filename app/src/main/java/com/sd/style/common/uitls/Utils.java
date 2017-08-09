package com.sd.style.common.uitls;

import android.content.Context;

import com.sd.style.common.app.MyApplication;

/**
 * @author: Rae.Ho
 * @description:
 * @date 2017/8/9  14:44
 */

public class Utils {

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Context getContext(){
        return MyApplication.getInstance();
    }
}
