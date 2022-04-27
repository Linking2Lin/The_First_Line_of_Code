package com.example.networktest.callback

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUtil {

    fun sendHttpRequest(address:String,listener: HttpCallbackListener){
        Log.d("当前线程", "sendHttpRequest: ${Thread.currentThread()}")
        thread {
            var connection:HttpURLConnection ?= null
            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 7777
                connection.readTimeout = 7777
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                listener.onFinish(response.toString())//在新开的这个线程内执行onFinish函数
                Log.d("线程", "sendHttpRequest: ${Thread.currentThread()}")
            }catch (e:Exception){
                e.printStackTrace()
                listener.onError(e)//在新开的这个线程内执行onError函数
            }finally {
                connection?.disconnect()
            }
        }
    }

                                                            //库中自带的回调接口
    fun sendOkHttpRequest(address: String,callback:okhttp3.Callback){
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(address)
            .build()
                                //内部开启子线程，并将最终的请求结果回调到okhttp3.Callback中
        client.newCall(request).enqueue(callback)
    }
}