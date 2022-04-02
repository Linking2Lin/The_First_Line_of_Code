package com.example.networktest.callbacks

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object Httputil {
    fun sendHttpRequest(address:String,listener:HttpCallbacklistener){
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
                //回调onFinish方法
                listener.onFinnishi(response.toString())
            }catch (e:Exception){
                listener.onError(e)
            }finally {
                connection?.disconnect()
            }
        }
    }

    fun sendPkHttpRequest(address: String,callback:okhttp3.Callback){
        val client = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }
}