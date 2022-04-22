package com.lins.sunnyweatherdemo.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 和风天气返回对象
 * @author Lin
 * @date 2022/4/20
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */


data class PlacesResponseHeFeng(val code:String,val location:List<LocationHF>,val refer:Refer)

data class LocationHF(
    val name:String,
    val id:String,
    val lat:String,//纬度
    val lon:String,//经度
    val adm2:String,
    val adm1:String,
    val country:String,
    val tz:String,
    val utcOffset:String,
    val isDst:String,
    val type:String,
    val rank:String,
    val fxLink:String
)


data class Refer(val sources:List<String>,val license:List<String>)


