package com.sd.style.module

import android.content.Context
import android.content.Intent
import android.view.View
import com.sd.style.R
import com.sd.style.common.base.BaseActivity
import com.sd.style.common.base.BasePresenter
import com.sd.style.module.home.v.fragment.HomeFragment
import com.sd.style.module.mine.v.fragment.MineFragment
import com.sd.style.module.run.v.RunFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {

    private var homeFragment: HomeFragment? = null
    private var mineFragment: MineFragment? = null
    private var runFragment : RunFragment ? = null


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
                switchRun()
            }
        }
    }

    private fun switchRun() {
        if (runFragment == null) {
            runFragment = RunFragment.newInstance()
        }
        switchFragment(R.id.main_container, currentFragment, runFragment)
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

    companion object {
        fun show(context: Context){
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}

