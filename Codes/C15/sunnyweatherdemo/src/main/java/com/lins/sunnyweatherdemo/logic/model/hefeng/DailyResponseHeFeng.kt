package com.lins.sunnyweatherdemo.logic.model.hefeng

/**
 *
 * @author Lin
 * @date 2022/4/21
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */

data class DailyResponseHeFeng(val code:String, val updateTime:String, val fxLink:String, val daily: Daily, val refer: Refer){

    data class Daily(
        val fxDate:String,
        val sunrise:String,
        val sunset:String,
        val moonrise:String,
        val moonset:String,
        val moonPhase:String,
        val moonOhaseIcon:String,
        val tempMax:String,
        val tempMin:String,
        val iconDay:String,
        val textDay:String,
        val iconNight:String,
        val textNight:String,
        val wind360Day:String,
        val windDirDay:String,
        val windScaleDay:String,
        val windSpeedDay:String,
        val wind360Night:String,
        val windDirNight:String,
        val windScaleNightMode:String,
        val windSpeedNight:String,
        val humidity:String,
        val precip:String,
        val pressure:String,
        val vis:String,
        val cloud:String,
        val uvIndex:String,
    )

    data class Refer(
        val sources:List<String>,
        val license:List<String>
    )
}