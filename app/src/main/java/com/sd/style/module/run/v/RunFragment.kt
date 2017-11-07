package com.sd.style.module.run.v

import android.widget.TextView
import com.sd.style.R
import com.sd.style.common.base.BaseFragment
import com.sd.style.common.base.BasePresenter

/**
 * Author: HeLei on 2017/11/7 16:55
 */
class RunFragment : BaseFragment(){

    override fun getLayoutId(): Int {
        return R.layout.fragment_run
    }

    override fun initView() {
        val textView = rootView.findViewById(R.id.textView) as TextView
    }

    override fun bindData() {
    }

    override fun initPresenter(): BasePresenter<*> {

    }

}