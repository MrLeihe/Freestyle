package com.sd.style.common.finger;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;

/**
 * Author: HeLei on 2017/10/22 11:10
 * 指纹工
 */

public class FingerprintUtils {

    private static CancellationSignal sCancellationSignal;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void callFingerPrint(@NonNull Context context, @NonNull OnCallbackListener listener){
        FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(context);
        //判断设备是否支持指纹识别
        if(!fingerprintManager.isHardwareDetected()) {
            listener.onSupportFailed();
            return;
        }
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        //判断指纹识别环境是否安全
        if(!keyguardManager.isKeyguardSecure()) {
            listener.onInsecurity();
            return;
        }
        //判断是否已经注册过指纹
        if(!fingerprintManager.hasEnrolledFingerprints()) {
            listener.onEnrollFailed();
            return;
        }
        listener.onAuthenticationStart();
        sCancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(null, 0, sCancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {

            //指纹识别过程出错
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                super.onAuthenticationError(errMsgId, errString);
                listener.onAuthenticationError(errMsgId, errString);
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                super.onAuthenticationHelp(helpMsgId, helpString);
                listener.onAuthenticationHelp(helpMsgId, helpString);
            }

            //指纹验证成功
            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                listener.onAuthenticationSucceeded(result);
            }

            //指纹验证失败
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                listener.onAuthenticationFailed();
            }
        }, null);
    }

    public interface OnCallbackListener{
        void onSupportFailed();
        void onInsecurity();
        void onEnrollFailed();
        void onAuthenticationStart();
        void onAuthenticationError(int errMsgId, CharSequence errString);
        void onAuthenticationFailed();
        void onAuthenticationHelp(int helpMsgId, CharSequence helpString);
        void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result);
    }

    public static void cancel(){
        if(sCancellationSignal != null) {
            sCancellationSignal.cancel();
        }
    }
}
