package com.sd.style.module.market.v.fragment

import com.sd.style.R
import com.sd.style.common.base.BaseFragment
import com.sd.style.common.base.BasePresenter

/**
 * Author: HeLei on 2017/11/30 10:53
 */
class MarketFragment : BaseFragment() {

    companion object {
        fun newInstance() : MarketFragment {
            return MarketFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_market
    }

    override fun initView() {
    }

    override fun bindData() {
    }

    override fun initPresenter(): BasePresenter<*>? {
        return null
    }
}