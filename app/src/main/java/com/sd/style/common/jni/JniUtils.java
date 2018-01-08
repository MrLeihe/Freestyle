package com.sd.style.common.jni;

/**
 * @author HeLei
 * @date 2018/1/4 14:42
 */

public class JniUtils {

    static {
        System.loadLibrary("native-lib");
    }

    public native String studyJni(String name, String age);
}
