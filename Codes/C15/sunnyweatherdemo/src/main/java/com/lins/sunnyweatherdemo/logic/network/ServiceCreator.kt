package com.lins.sunnyweatherdemo.logic.network

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

    private const val BASE_URL = "https://api.caiyunaoo.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass:Class<T>):T = retrofit.create(serviceClass)

    inline fun <reified T> create():T = create(T::class.java)
}