package com.sd.style.common.db.bean

import com.sd.style.module.model.BaseBean

/**
 * Author: HeLei on 2017/11/10 15:41
 */
data class PointEntity(var lng : Double, var lat : Double, var time : Long,
                       var province : String, var city : String, var area : String) : BaseBean(){

    companion object {
        val LNG = "lng"
    }
}