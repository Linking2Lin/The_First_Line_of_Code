package com.lins.sunnyweatherdemo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 用来获取全局context
 * @author Lin
 * @date 2022/4/12
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
class SunnyWeatherApplication:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        /**和风天气KEY*/
        const val KEY = "607b7784a83347ce87ca042671a5cde2"
        /**高德天气KEY*/
        //const val KEY = "14fd98ccb4ed94f7ea275a92756d038d"
        /**彩云天气Token*/
        const val Token = "8tdGEpTpH9CMB4an"
    }

    //在应用启动时会执行，这样伴生对象内的context就会得到程序全局context
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}