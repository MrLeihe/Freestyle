package com.sd.style.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sd.style.R;
import com.sd.style.common.base.BaseActivity;
import com.sd.style.common.base.BasePresenter;
import com.sd.style.common.finger.FingerprintUtils;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @BindView(R.id.start_fingerprint)
    TextView start_fingerprint;

    private AlertDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        start_fingerprint.setOnClickListener(v ->
                startFingerPrint()
        );
    }

    private void startFingerPrint() {
        FingerprintUtils.callFingerPrint(TestActivity.this, new FingerprintUtils.OnCallbackListener() {
            @Override
            public void onSupportFailed() {
                showToast("当前设备不支持指纹");
            }

            @Override
            public void onInsecurity() {
                showToast("当前设备为处于安全保护中");
            }

            @Override
            public void onEnrollFailed() {
                showToast("请到设置中设置指纹");
            }

            @Override
            public void onAuthenticationStart() {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                View view = LayoutInflater.from(TestActivity.this).inflate(R.layout.layout_fingerprint, null);
                builder.setView(view);
                builder.setCancelable(false);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FingerprintUtils.cancel();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                showToast(errString.toString());

            }

            @Override
            public void onAuthenticationFailed() {
                showToast("指纹识别失败");
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                showToast(helpString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                showToast("指纹识别成功");
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void bindData() {
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    public static void showActivity(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }
}
