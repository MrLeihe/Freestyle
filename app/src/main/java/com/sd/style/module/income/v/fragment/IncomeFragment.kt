package com.sd.style.module.income.v.fragment

import com.sd.style.R
import com.sd.style.common.base.BaseFragment
import com.sd.style.common.base.BasePresenter

/**
 * Author: HeLei on 2017/11/30 11:11
 */
class IncomeFragment : BaseFragment(){

    companion object {
        fun newInstance() : IncomeFragment{
            return IncomeFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_income
    }

    override fun initView() {

    }

    override fun bindData() {

    }

    override fun initPresenter(): BasePresenter<*>? {
        return null
    }

}