package com.sd.style.module

import android.view.View
import android.widget.*
import butterknife.BindView
import com.sd.style.R
import com.sd.style.common.base.BaseActivity
import com.sd.style.common.base.BasePresenter
import com.sd.style.module.home.v.fragment.HomeFragment
import com.sd.style.module.mine.v.fragment.MineFragment

class MainActivity : BaseActivity(), View.OnClickListener {

    @BindView(R.id.main_container)
    internal var main_container: FrameLayout? = null
    @BindView(R.id.ll_switch_home)
    internal var ll_switch_home: LinearLayout? = null
    @BindView(R.id.iv_home)
    internal var iv_home: ImageView? = null
    @BindView(R.id.tv_home)
    internal var tv_home: TextView? = null
    @BindView(R.id.ll_switch_income)
    internal var ll_switch_income: LinearLayout? = null
    @BindView(R.id.iv_income)
    internal var iv_income: ImageView? = null
    @BindView(R.id.tv_income)
    internal var tv_income: TextView? = null
    @BindView(R.id.rl_switch_run)
    internal var rl_switch_run: RelativeLayout? = null
    @BindView(R.id.iv_run_stroke)
    internal var iv_run_stroke: ImageView? = null
    @BindView(R.id.iv_run)
    internal var iv_run: ImageView? = null
    @BindView(R.id.ll_switch_market)
    internal var ll_switch_market: LinearLayout? = null
    @BindView(R.id.iv_market)
    internal var iv_market: ImageView? = null
    @BindView(R.id.tv_market)
    internal var tv_market: TextView? = null
    @BindView(R.id.ll_switch_mine)
    internal var ll_switch_mine: LinearLayout? = null
    @BindView(R.id.iv_mine)
    internal var iv_mine: ImageView? = null
    @BindView(R.id.tv_mine)
    internal var tv_mine: TextView? = null

    private var homeFragment: HomeFragment? = null
    private var mineFragment: MineFragment? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        ll_switch_home!!.setOnClickListener(this)
        ll_switch_income!!.setOnClickListener(this)
        ll_switch_market!!.setOnClickListener(this)
        ll_switch_mine!!.setOnClickListener(this)
        rl_switch_run!!.setOnClickListener(this)
    }

    override fun bindData() {

    }

    override fun initPresenter(): BasePresenter<*>? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ll_switch_home -> switchHome()
            R.id.ll_switch_income -> {
            }
            R.id.ll_switch_market -> {
            }
            R.id.ll_switch_mine -> switchMine()
            R.id.rl_switch_run -> {
            }
        }
    }

    private fun switchMine() {
        if (mineFragment == null) {
            mineFragment = MineFragment.newInstance()
        }
        switchFragment(R.id.main_container, currentFragment, mineFragment)
    }

    private fun switchHome() {
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance()
        }
        switchFragment(R.id.main_container, currentFragment, homeFragment)
    }
}
