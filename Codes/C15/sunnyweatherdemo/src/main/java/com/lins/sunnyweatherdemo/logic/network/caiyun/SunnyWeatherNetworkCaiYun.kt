package com.lins.sunnyweatherdemo.logic.network.caiyun

import com.lins.sunnyweatherdemo.logic.network.hefeng.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetworkCaiYun {
    //创建一个placeService接口动态管理对象
    private val placeService = ServiceCreatorCaiYun.create<PlaceServiceCaiYun>()

    private val weatherService = ServiceCreatorCaiYun.create<CaiYunWeatherService>()

    //挂起函数，用来发起搜索城市请求
    suspend fun searchPlaces(query:String) = placeService.searchPlaces(query).await()

    suspend fun getDailyWeather(lng:String,lat:String) = weatherService.getDailyWeather(lng, lat).await()

    suspend fun getRealTimeWeather(lng: String,lat: String) = weatherService.getRealtimeWeather(lng, lat).await()

    //将await定义为Call<T>的扩展函数,实现回调简化
    private suspend fun <T> Call<T>.await():T{
               //在协程作用域内才能调用，将当前协程挂起，然后在一个普通的线程中执行Lambda表达式中的代码
        return suspendCoroutine { continuation ->
            //该函数内部开启子线程，在子线程中执行HTTP请求，然后将请求结果回调到Callback中
            enqueue(object : Callback<T>{
                //请求结果是响应：
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}