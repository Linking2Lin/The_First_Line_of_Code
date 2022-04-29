package com.lins.sunnyweatherdemo.logic.model.caiyun

import com.google.gson.annotations.SerializedName

/**
 * 实时数据模型
 * @author Lin
 * @date 2022/4/29
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */

data class CaiYunRealtimeResponse(val status:String,val result:Result) {

    data class Result(val realTime: Realtime)

    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}