package com.sd.style.module.home.v.fragment


import com.sd.style.R
import com.sd.style.common.base.BaseFragment
import com.sd.style.common.base.BasePresenter


/**
 * Author: HeLei on 2017/10/16 18:25
 */

class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

    }

    override fun bindData() {
    }

    override fun initPresenter(): BasePresenter<*>? {
        return null
    }

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
