package com.example.retrofittest

import retrofit2.Call
import retrofit2.http.GET

interface AppService {
    @GET("get_data.json")//表示调用该方法会发起一条请求，请求地址为注解中的参数（相对路径）
    fun getAppData():Call<List<App>> //返回值声明为Retrofit内置的Call类型，并通过泛型来指定服务器返回数据应该转换为什么对象
}