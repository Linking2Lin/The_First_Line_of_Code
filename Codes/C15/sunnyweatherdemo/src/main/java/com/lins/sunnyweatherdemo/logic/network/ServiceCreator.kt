package com.lins.sunnyweatherdemo.logic.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit构造器
 * @author Lin
 * @date 2022/4/12
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
object ServiceCreator {

    private const val BASE_URL = "https://geoapi.qweather.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createIn())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass:Class<T>):T = retrofit.create(serviceClass)

    inline fun <reified T> create():T = create(T::class.java)

    //获取Service接口的动态代理对象：
    //val service = ServiceCreator.create<Service>()，然后调用Service内各方法

    private fun createIn():OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

}