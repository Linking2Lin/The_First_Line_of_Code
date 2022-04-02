package com.example.retrofittest.examples


import com.example.retrofittest.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExampleService {

    //对应 http://example.com/get_data.json
    @GET("get_data.json")
    fun getData(): retrofit2.Call<Data>

    //对应 http://example.com/<page>/get_data.json
    @GET("{page}/get_data.json")
    fun getData(@Path("page") page:Int):Call<Data>//这样使用该方法发起请求时，Retrofit会自动将page参数替换到连接内page占位符的位置上

    //对应 http://example.com/get_data.json?u=<user>&t=<token>
    @GET("get_data.json")
    fun getData(@Query("u") user:String,@Query("t") token:String):Call<Data>//发起请求时，会将user和token填充到连接里组成完整的地址
}