package com.lins.sunnyweatherdemo.logic.network

import com.lins.sunnyweatherdemo.SunnyWeatherApplication
import com.lins.sunnyweatherdemo.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}