package com.lins.sunnyweatherdemo.logic.data.layer.entry

import androidx.lifecycle.liveData
import com.lins.sunnyweatherdemo.logic.model.caiyun.Place
import com.lins.sunnyweatherdemo.logic.network.caiyun.SunnyWeatherNetworkCaiYun
import com.lins.sunnyweatherdemo.logic.network.hefeng.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

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
}