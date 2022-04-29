package com.lins.sunnyweatherdemo.logic.data.layer.entry

import android.util.Log
import androidx.lifecycle.liveData
import com.lins.sunnyweatherdemo.logic.model.caiyun.Place
import com.lins.sunnyweatherdemo.logic.model.caiyun.Weather
import com.lins.sunnyweatherdemo.logic.network.caiyun.SunnyWeatherNetworkCaiYun
import com.lins.sunnyweatherdemo.logic.network.hefeng.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * 
 * @author Lin
 * @date 2022/4/28
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
object RespositoryCaiYun {
                                     //自动构建并返回一个livedata对象，在代码块中提供一个挂起函数的上下文
    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        val result = try {
            val placeResponse = SunnyWeatherNetworkCaiYun.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }

        emit(result)//发射包装结果
    }

    fun refreshWeather(lng:String,lat:String) = liveData(Dispatchers.IO){
        val result = try {
            coroutineScope {
                val deferredRealtime = async {
                    SunnyWeatherNetworkCaiYun.getRealTimeWeather(lng, lat)
                }

                val deferredDaily = async {
                    SunnyWeatherNetworkCaiYun.getDailyWeather(lng, lat)
                }

                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()

                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                    Log.d("status", "refreshWeather: ${realtimeResponse.result.realTime}  ${dailyResponse.result.daily} ")

                    val weather = Weather(realtimeResponse.result.realTime,dailyResponse.result.daily)
                    Log.d("status", "refreshWeather: ${realtimeResponse.status}  ${dailyResponse.status}   ${weather.realtime.temperature}")

                    Result.success(weather)
                }else{
                    Result.failure(
                        RuntimeException(
                            "realtime Status is ${realtimeResponse.status}" + "daily Status is ${dailyResponse.status}"
                        )
                    )
                }
            }
        }catch (e:Exception){
            Result.failure<Weather>(e)
        }
        emit(result)
    }
}