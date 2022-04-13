package com.lins.sunnyweatherdemo.logic

import androidx.lifecycle.liveData
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
            if (placeResponse.status == "OK"){
                val places = placeResponse.places
                Result.success(placeResponse)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}