package com.sd.style.module.mine.v.fragment

import android.os.Handler
import android.os.Message
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import com.sd.style.R
import com.sd.style.common.base.BaseFragment
import com.sd.style.common.base.BasePresenter
import com.sd.style.common.uitls.UIUtils
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Author: HeLei on 2017/10/24 00:23
 */

class MineFragment : BaseFragment() {

    private var isRunning: Boolean = false

    val fromUri: String
        external get

    private var progress: Int = 0
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            progress++
            sale_view!!.setScale(progress.toFloat())
            this.removeMessages(0)
            if (progress < 100) {
                this.sendEmptyMessageDelayed(0, 100)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
        tv_pause_wave!!.setOnClickListener { v ->
            if (isRunning) {
                wave_view!!.pause()
                isRunning = false
                tv_pause_wave!!.text = "开始"
            } else {
                wave_view!!.start()
                isRunning = true
                tv_pause_wave!!.text = "暂停"
            }
        }

        tv_jni_test!!.setOnClickListener { v ->
            val fromUri = fromUri
            tv_jni_test!!.text = fromUri
        }
    }

    override fun bindData() {
        handler.sendEmptyMessage(0)
        wave_view!!.setDuration(2000)
        wave_view!!.setMinWaveRadius(UIUtils.dp2px(1f).toFloat())
        wave_view!!.setMaxWaveRadius(UIUtils.dp2px(50f).toFloat())
        wave_view!!.setInterpolator(LinearOutSlowInInterpolator())
        wave_view!!.setColor(R.color.theme_color)
        wave_view!!.start()
        isRunning = true
    }

    override fun initPresenter(): BasePresenter<*>? {
        return null
    }

    override fun onResume() {
        super.onResume()
        wave_view!!.start()
    }

    override fun onPause() {
        super.onPause()
        wave_view!!.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        wave_view!!.destroy()
    }

    companion object {

        init {
            System.loadLibrary("hello_jni")
        }

        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }
}
