package com.example.highkills

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 全局Context类
 * @author Lin
 * @date 2022/4/8
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
class MyApplication:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}