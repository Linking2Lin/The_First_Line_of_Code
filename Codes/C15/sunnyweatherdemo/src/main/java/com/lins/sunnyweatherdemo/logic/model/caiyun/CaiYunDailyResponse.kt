package com.lins.sunnyweatherdemo.logic.model.caiyun

import com.google.gson.annotations.SerializedName

/**
 * 未来天气数据模型
 * @author Lin
 * @date 2022/4/29
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */

data class CaiYunDailyResponse(val status:String,val result: ResultD) {

    data class ResultD(val daily: Daily)

    data class Daily(
        val temperature: List<Temperature>,
        val skycon: List<Skycon>,
        @SerializedName("life_index") val lifeIndex: LifeIndex
    )

    data class Temperature(val max: Float, val min: Float)

    data class Skycon(val value: String, val date: String)

    data class LifeIndex(
        val coldRisk: List<LifeDescription>,
        val carWashing: List<LifeDescription>,
        val ultraviolet: List<LifeDescription>,
        val dressing: List<LifeDescription>
    )

    data class LifeDescription(val desc: String)
}