package com.lins.sunnyweatherdemo.logic.model

/**
 * 实时天气数据模型
 * @author Lin
 * @date 2022/4/21
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */

data class RealtimeResponseHeFeng(
    val code:String,//API状态码
    val updateTime:String,//当前API的最近更新时间
    val fxLink:String,//当前数据的响应式页面
    val now:Now,//当前天气数据对象
    val refer:Refer//参考
    ){

    data class Now(
        val obsTime:String,//数据观测时间
        val temp:String,//温度，默认为摄氏度
        val feelsLike:String,//体感温度
        val icon:String,//天气状况和图标的代码
        val text:String,//天气状况的文字描述
        val wind360:String,//风向360角度
        val windDir:String,//风向
        val windScale:String,//风力等级
        val windSpeed:String,//风速
        val humidity:String,//相对湿度
        val precip:String,//当前小时累计降水量
        val pressure:String,//大气压强
        val vis:String,//能见度
        val cloud:String,//云量
        val dew:String,//露点温度
    )

    /**
     * 参考
     */
    data class Refer(
        val sources:List<String>,//数据来源
        val license:List<String>//许可
        )
}

