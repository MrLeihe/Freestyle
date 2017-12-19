package com.sd.style.module.mine.v.fragment

import android.os.Handler
import android.os.Message
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import com.sd.style.R
import com.sd.style.common.base.BaseFragment
import com.sd.style.common.base.BasePresenter
import com.sd.style.common.db.bean.PointEntity
import com.sd.style.common.db.run.RunDao
import com.sd.style.common.uitls.ToastUtils
import com.sd.style.common.uitls.UIUtils
import kotlinx.android.synthetic.main.fragment_mine.*
import java.lang.ref.SoftReference

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

        /**
         * 添加数据到数据库
         */
        tv_add_data!!.setOnClickListener { v ->
            ToastUtils.showLongToast("添加数据")
            val pointEntity = PointEntity(12.0, 23.0, 1000,
                    "江西", "吉安", "永新", "100m")
            runDao?.addPoint(pointEntity)
        }

        /**
         * 查询
         */
        tv_query_data!!.setOnClickListener { v ->
            //runDao?.deletePoint(arrayOf("100m"))
            runDao?.queryPoint("江西")
        }
    }

    private var runDao: RunDao? = null

    override fun bindData() {
        handler.sendEmptyMessage(0)
        wave_view!!.setDuration(2000)
        wave_view!!.setMinWaveRadius(UIUtils.dp2px(1f).toFloat())
        wave_view!!.setMaxWaveRadius(UIUtils.dp2px(50f).toFloat())
        wave_view!!.setInterpolator(LinearOutSlowInInterpolator())
        wave_view!!.setColor(R.color.theme_color)
        wave_view!!.start()
        isRunning = true
        val sf = SoftReference<RunDao>(RunDao(mContext))
        runDao = sf.get()
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
