package com.lins.sunnyweatherdemo.logic.data.layer.entry

import android.util.Log
import androidx.lifecycle.liveData
import com.lins.sunnyweatherdemo.logic.model.hefeng.WeatherHeFeng
import com.lins.sunnyweatherdemo.logic.network.hefeng.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * 仓库层的统一封装入口
 * @author Lin
 * @date 2022/4/13
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
object Repository {
                                    //该函数会自动构建并返回一个LiveData对象，同时在代码块中提供一个挂起函数的上下文
    fun searchPlaces(query:String) = fire(Dispatchers.IO){
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            Log.d("获取数据", "searchPlaces: $placeResponse")
            if (placeResponse.code == "200"){
                val places = placeResponse.location
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.code}"))
            }
    }

    fun refreshWeather(lng:String,lat:String) = fire(Dispatchers.IO){
            coroutineScope {
                val deferredRealtime = async {
                    SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily = async {
                    SunnyWeatherNetwork.getDailyWeather(lng, lat)
                }

                val realtimeResponseHeFeng = deferredRealtime.await()
                val dailyResponseHeFeng = deferredDaily.await()

                if (realtimeResponseHeFeng.code == "200"&& dailyResponseHeFeng.code == "200"){
                    val wearther = WeatherHeFeng(realtimeResponseHeFeng.now,dailyResponseHeFeng.daily)
                    Result.success(wearther)
                }else{
                    Result.failure(
                        RuntimeException(
                            "code is ${realtimeResponseHeFeng.code}  ${dailyResponseHeFeng.code}"
                        )
                    )
                }
            }
    }

    private fun <T> fire(context:CoroutineContext, block:suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        }catch (e:Exception){
            Result.failure<T>(e)
        }
        emit(result)
    }
}