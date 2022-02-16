package com.example.myapplication

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUtil {
    fun sendHtpRequest(address:String,listener:HttpCallbackListener){
        thread {
            //开启线程执行网络操作
            var connection:HttpURLConnection?=null
            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                //回调onFinish
                listener.onFinish(response.toString())
            }catch (e:Exception){
                e.printStackTrace()
                //回调onError
                listener.onError(e)
            }finally {
                connection?.disconnect()
            }
        }
    }
    fun sendOkHttpRequest(address:String,callback:okhttp3.Callback){
        val client = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }
}