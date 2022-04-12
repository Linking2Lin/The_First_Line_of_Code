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

        //令牌值，用来与服务器交互
        const val TOKEN = " "
    }

    //在应用启动时会执行，这样上面的context就会得到程序全局context
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}