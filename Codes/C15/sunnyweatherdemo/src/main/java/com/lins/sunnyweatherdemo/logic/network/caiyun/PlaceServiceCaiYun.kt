package com.lins.sunnyweatherdemo.logic.network.caiyun

import com.lins.sunnyweatherdemo.SunnyWeatherApplication
import com.lins.sunnyweatherdemo.logic.model.caiyun.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceServiceCaiYun {

    @GET("v2/place?token=${SunnyWeatherApplication.Token}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String):Call<PlaceResponse>
}