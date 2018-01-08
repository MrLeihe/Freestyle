package com.sd.style.module

import android.content.Context
import android.content.Intent
import android.view.View
import com.facebook.stetho.common.LogUtil
import com.sd.style.R
import com.sd.style.common.base.BaseActivity
import com.sd.style.common.base.BaseFragment
import com.sd.style.common.base.BasePresenter
import com.sd.style.common.uitls.ContextCompactUtils
import com.sd.style.common.uitls.StatusBarUtils
import com.sd.style.module.home.v.fragment.HomeFragment
import com.sd.style.module.income.v.fragment.IncomeFragment
import com.sd.style.module.market.v.fragment.MarketFragment
import com.sd.style.module.mine.v.fragment.MineFragment
import com.sd.style.module.run.v.RunFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {

    private var homeFragment: HomeFragment? = null
    private var mineFragment: MineFragment? = null
    private var runFragment: RunFragment? = null
    private var marketFragment: MarketFragment? = null
    private var incomeFragment: IncomeFragment? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val result = StatusBarUtils.FlymeSetStatusBarLightMode(window, true)
        LogUtil.e("result----------->" + result)
        ll_switch_home!!.setOnClickListener(this)
        ll_switch_income!!.setOnClickListener(this)
        ll_switch_market!!.setOnClickListener(this)
        ll_switch_mine!!.setOnClickListener(this)
        rl_switch_run!!.setOnClickListener(this)
    }

    override fun bindData() {
        switchBottom(ll_switch_home)
    }

    override fun initPresenter(): BasePresenter<*>? {
        return null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ll_switch_home,
            R.id.ll_switch_income,
            R.id.ll_switch_market,
            R.id.ll_switch_mine,
            R.id.rl_switch_run -> switchBottom(v)
        }
    }

    private fun switchBottom(v: View) {
        switchMarket(v == ll_switch_market)
        switchIncome(v == ll_switch_income)
        switchHome(v == ll_switch_home)
        switchMine(v == ll_switch_mine)
        switchRun(v == rl_switch_run)
    }

    private fun switchMarket(isChecked: Boolean) {
        if (isChecked) {
            if (marketFragment == null) {
                marketFragment = MarketFragment.newInstance()
            }
            switchFragment(marketFragment!!)
            iv_market.setImageResource(R.drawable.ad_market_sel)
            tv_market.setTextColor(ContextCompactUtils.getColor(this, R.color.theme_color))
        } else {
            iv_home.setImageResource(R.drawable.ad_market_dark)
            tv_market.setTextColor(ContextCompactUtils.getColor(this, R.color.theme_gray_color))
        }
    }

    private fun switchIncome(isChecked: Boolean) {
        if (isChecked) {
            if (incomeFragment == null) {
                incomeFragment = IncomeFragment.newInstance()
            }
            switchFragment(incomeFragment!!)
            iv_income.setImageResource(R.drawable.earning_checked)
            tv_income.setTextColor(ContextCompactUtils.getColor(this, R.color.theme_color))
        } else {
            iv_income.setImageResource(R.drawable.earing_unchecked)
            tv_income.setTextColor(ContextCompactUtils.getColor(this, R.color.theme_gray_color))
        }
    }

    private fun switchMine(isChecked: Boolean) {
        if (isChecked) {
            if (mineFragment == null) {
                mineFragment = MineFragment.newInstance()
            }
            switchFragment(mineFragment!!)
            iv_mine.setImageResource(R.drawable.user_checked)
            tv_mine.setTextColor(ContextCompactUtils.getColor(this, R.color.theme_color))
        } else {
            iv_mine.setImageResource(R.drawable.user_unchecked)
            tv_mine.setTextColor(ContextCompactUtils.getColor(this, R.color.theme_gray_color))
        }
    }

    private fun switchHome(isChecked: Boolean) {
        if (isChecked) {
            if (homeFragment == null) {
                homeFragment = HomeFragment.newInstance()
            }
            switchFragment(homeFragment!!)
            iv_home.setImageResource(R.drawable.home_checked)
            tv_home.setTextColor(ContextCompactUtils.getColor(this, R.color.theme_color))
        } else {
            iv_home.setImageResource(R.drawable.home_unchecked)
            tv_home.setTextColor(ContextCompactUtils.getColor(this, R.color.theme_gray_color))
        }
    }

    private fun switchRun(isChecked: Boolean) {
        if (isChecked) {
            if (runFragment == null) {
                runFragment = RunFragment.newInstance()
            }
            switchFragment(runFragment!!)
        }
    }

    private fun switchFragment(to: BaseFragment) {
        switchFragment(R.id.main_container, currentFragment, to)
    }

    companion object {
        fun show(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}

