package com.lins.sunnyweatherdemo.logic.network.hefeng

import com.lins.sunnyweatherdemo.SunnyWeatherApplication
import com.lins.sunnyweatherdemo.logic.model.PlacesResponseHeFeng
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PlaceService {

    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json"
    )
    @GET("v2/city/lookup?key=${SunnyWeatherApplication.KEY}&lan=zh")//后面会自动加上位置参数
    fun searchPlaces(@Query("location") location: String): Call<PlacesResponseHeFeng>
}