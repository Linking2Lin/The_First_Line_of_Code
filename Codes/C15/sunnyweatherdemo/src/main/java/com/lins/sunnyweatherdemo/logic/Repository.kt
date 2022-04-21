package com.lins.sunnyweatherdemo.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.lins.sunnyweatherdemo.logic.model.LocationHF
import com.lins.sunnyweatherdemo.logic.model.Place
import com.lins.sunnyweatherdemo.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

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
    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            Log.d("获取数据", "searchPlaces: $placeResponse")
            if (placeResponse.code == "200"){
                val places = placeResponse.location
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.code}"))
            }
        }catch (e:Exception){
            Result.failure<List<LocationHF>>(e)
        }
        emit(result)
    }
}