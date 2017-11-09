package com.sd.style.module

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import com.sd.style.R
import com.sd.style.common.base.BaseActivity
import com.sd.style.common.base.BasePresenter
import com.sd.style.common.finger.FingerprintUtils
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseActivity() {

    var dialog: AlertDialog? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
        start_fingerprint!!.setOnClickListener { _ -> startFingerPrint() }
    }

    private fun startFingerPrint() {
        FingerprintUtils.callFingerPrint(this@TestActivity, object : FingerprintUtils.OnCallbackListener {
            override fun onSupportFailed() {
                showToast("当前设备不支持指纹")
            }

            override fun onInsecurity() {
                showToast("当前设备为处于安全保护中")
            }

            override fun onEnrollFailed() {
                showToast("请到设置中设置指纹")
            }

            override fun onAuthenticationStart() {
                val builder = AlertDialog.Builder(this@TestActivity)
                val view = LayoutInflater.from(this@TestActivity).inflate(R.layout.layout_fingerprint, null)
                builder.setView(view)
                builder.setCancelable(false)
                builder.setNegativeButton("取消") { dialog, which -> FingerprintUtils.cancel() }
                dialog = builder.create()
                dialog!!.show()
            }

            override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
                showToast(errString.toString())
            }

            override fun onAuthenticationFailed() {
                showToast("指纹识别失败")
            }

            override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
                showToast(helpString.toString())
            }

            override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult) {
                showToast("指纹识别成功")
                if (dialog != null && dialog!!.isShowing) {
                    dialog!!.dismiss()
                }
            }
        })
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun bindData() {
    }

    override fun initPresenter(): BasePresenter<*>? {
        return null
    }

    companion object {

        fun showActivity(context: Context) {
            val intent = Intent(context, TestActivity::class.java)
            context.startActivity(intent)
        }
    }
}
