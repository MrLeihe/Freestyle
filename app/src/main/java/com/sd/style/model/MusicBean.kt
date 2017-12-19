package com.sd.style.model

import android.widget.Toast
import com.sd.style.common.app.BaseApplication

/**
 * Author: HeLei on 2017/11/7 17:14
 */
open class MusicBean(name : String, age : Int) : BaseBean(){

    fun showToast(message: String, time : Int = Toast.LENGTH_SHORT){
        Toast.makeText(BaseApplication.getInstance(), message, time).show()
    }
}