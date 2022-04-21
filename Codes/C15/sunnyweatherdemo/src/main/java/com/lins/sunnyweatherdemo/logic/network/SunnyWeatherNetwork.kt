package com.lins.sunnyweatherdemo.logic.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 统一网络数据源访问入口，封装所有的网络请求
 * @author Lin
 * @date 2022/4/13
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
object SunnyWeatherNetwork {
    //创建一个PlaceService的动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>()
    //调用代理对象的searchPlaces，发起搜索城市请求
    suspend fun searchPlaces(query:String) = placeService.searchPlaces(query).await()
    //简化Retrofit回调写法
    /*
     * 当外部调用SunnyWeatherNetwork.searchPlaces()时，Retrofit会立刻发起网络请求，同时当前协程阻塞，
     * 直到服务器响应请求后，await()将解析出来的数据模型对象取出并返回，同时恢复当前协程的执行
     * searchPlaces()函数在得到awit()的返回值后，将数据返回到上一层
     */
    private suspend fun <T> Call<T>.await():T{//给Call里扩展了一个await函数
        return suspendCoroutine { continuation ->//当前协程挂起，开启普通线程来执行下面的操作
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    Log.d("获取", "onResponse: ${response.body().toString()}")
                    val body = response.body()
                    if (body!=null){
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException(RuntimeException("response body is wrong"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    private val weatherService = ServiceCreatorWeather.create<WeatherService>()

    suspend fun getDailyWeather(lng:String,lat:String) = weatherService.getDailyWeather(lng,lat).await()

    suspend fun getRealtimeWeather(lng:String,lat:String) = weatherService.getRealTimeWeather(lng,lat).await()
}