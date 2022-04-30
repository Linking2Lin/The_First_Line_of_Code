package com.lins.sunnyweatherdemo.logic.dao.caiyun

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.lins.sunnyweatherdemo.SunnyWeatherApplication
import com.lins.sunnyweatherdemo.logic.model.caiyun.Place

/**
 * 指向sunny_weather的DAO
 * @author Lin
 * @date 2022/4/30
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
object PlaceDao {

    private fun sharedPreferences() = SunnyWeatherApplication.context.getSharedPreferences("sunny_weather",
        Context.MODE_PRIVATE)

    /**
     * 存储Place对象
     */
    fun savePlace(place: Place){
        sharedPreferences().edit {
            putString("place",Gson().toJson(place))//保存的是转为JSON字符串的Place对象
        }
    }

    /**
     * 取出Place对象
     */
    fun getSavedPlace():Place{

        val placeJson = sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

}