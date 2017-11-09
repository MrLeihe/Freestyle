package com.sd.style.module.run.v

import com.sd.style.R
import com.sd.style.common.base.BaseFragment
import com.sd.style.common.base.BasePresenter
import com.sd.style.module.run.p.RunPresenter

/**
 * Author: HeLei on 2017/11/7 16:55
 */
open class RunFragment : BaseFragment(){

    companion object {
        fun newInstance() : RunFragment{
            return RunFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_run
    }

    override fun initView() {

    }

    override fun bindData() {
    }

    override fun initPresenter(): BasePresenter<*> {
        return RunPresenter()
    }

}