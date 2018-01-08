package com.sd.style.module.home.v.fragment


import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.sd.style.R
import com.sd.style.common.base.BaseFragment
import com.sd.style.common.base.BasePresenter
import kotlinx.android.synthetic.main.base_title.*


/**
 * Author: HeLei on 2017/10/16 18:25
 */

class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        //强转为AppCompatActivity
        setHasOptionsMenu(true)
        initToolbar()
        changeTitleHeight()
    }

    private fun initToolbar() : Toolbar{
        val activity :AppCompatActivity? = mContext as AppCompatActivity
        activity?.setSupportActionBar(base_toolbar)
        base_toolbar.title = "首页"
        base_toolbar.setNavigationIcon(R.drawable.title_back)
        return base_toolbar
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
