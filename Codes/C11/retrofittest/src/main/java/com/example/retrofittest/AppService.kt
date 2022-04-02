package com.example.retrofittest

import com.example.retrofittest.tool.App
import retrofit2.Call
import retrofit2.http.GET

interface AppService {
    @GET("get_data.json")//表示调用该方法时会发起一条get请求，请求的相对地址为括号中的内容
    fun getAppData():Call<List<App>>//返回值通过泛型指定服务器响应的数据该转换为什么对象
}