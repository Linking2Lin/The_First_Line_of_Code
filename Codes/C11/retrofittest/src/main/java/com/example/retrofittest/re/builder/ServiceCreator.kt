package com.example.retrofittest.re.builder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https:www.baidu.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())//为Retrofit添加转化器
        .build()

    //当需要创建动态代理对象时调用该方法
    fun <T> create(serviceClass:Class<T>):T = retrofit.create(serviceClass)

    //利用泛型实例化来简化
    inline fun <reified T> create():T = create(T::class.java)

    //正常情况下通过回调来发起网络请求：
    val appService = ServiceCreator.create<Int>()
}