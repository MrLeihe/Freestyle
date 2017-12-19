package com.sd.style.common.db.bean

import com.sd.style.model.BaseBean

/**
 * Author: HeLei on 2017/11/10 15:41
 */
data class PointEntity(var lng : Double, var lat : Double, var time : Long,
                       var province : String, var city : String, var area : String,
                       var distance : String) : BaseBean(){

    companion object {
        val LNG = "lng"
        val LAT = "lat"
        val TIME = "time"
        val PROVINCE = "province"
        val CITY = "city"
        val AREA = "area"
        val DISTANCE = "distance"
    }
}