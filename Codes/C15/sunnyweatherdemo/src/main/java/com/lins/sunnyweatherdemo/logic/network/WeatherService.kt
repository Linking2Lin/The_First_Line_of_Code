package com.lins.sunnyweatherdemo.logic.network

import com.lins.sunnyweatherdemo.SunnyWeatherApplication
import com.lins.sunnyweatherdemo.logic.model.DailyResponseHeFeng
import com.lins.sunnyweatherdemo.logic.model.RealtimeResponseHeFeng
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WeatherService {

    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json"
    )
    @GET("v7/weather/now?key=${SunnyWeatherApplication.KEY}&location={lng}{lat}")
    fun getRealTimeWeather(@Path("lng")lng:String,@Path("lat")lat:String):Call<RealtimeResponseHeFeng>

    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json"
    )
    @GET("v7/weather/3d?key=${SunnyWeatherApplication.KEY}&location={lng}{lat}")
    fun getDailyWeather(@Path("lng")lng:String,@Path("lat")lat:String):Call<DailyResponseHeFeng>
}