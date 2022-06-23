package com.lins.sunnyweatherdemo.logic.network.caiyun

import com.lins.sunnyweatherdemo.SunnyWeatherApplication
import com.lins.sunnyweatherdemo.logic.model.caiyun.CaiYunDailyResponse
import com.lins.sunnyweatherdemo.logic.model.caiyun.CaiYunRealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 获取实时和当天天气接口
 */
interface CaiYunWeatherService {

    @GET("v2.5/${SunnyWeatherApplication.Token}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng:String,@Path("lat") lat:String):Call<CaiYunRealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.Token}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng:String,@Path("lat") lat: String ):Call<CaiYunDailyResponse>
}