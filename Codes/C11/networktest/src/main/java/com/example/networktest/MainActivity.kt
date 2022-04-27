package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.networktest.callback.HttpCallbackListener
import com.example.networktest.callback.HttpUtil
import com.example.networktest.databinding.ActivityMainBinding
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendRequestBtn.setOnClickListener {
            //sendRequestWithHttpURLConnection()
            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithHttpURLConnection(){
        thread {//开启线程执行网络请求操作
            var connection:HttpURLConnection ?= null
            try {
                val response = StringBuilder()//返回数据
                var url = URL("https:www.baidu.com")//构建URL对象
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 7777//连接超时时间
                connection.readTimeout = 7777//读取超时时间

                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                        Log.d("当前行数", "sendRequestWithHttpURLConnection: ${response.length}")
                    }
                }
                showReponse(response.toString())

            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
        }

        HttpUtil.sendHttpRequest("Http:\\www.baidu.com",object : HttpCallbackListener{
            override fun onFinish(response: String) {
                TODO("Not yet implemented")
            }

            override fun onError(e: Exception) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun sendRequestWithOkHttp(){
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.bing.com")
                    .build()

                val response = client.newCall(request).execute()

                val ressponseData = response.body?.string()//将返回的数据对象转为字符串，返回可能为空，因此做判空处理

                if (ressponseData != null){
                    showReponse(ressponseData)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        HttpUtil.sendOkHttpRequest("Http:\\www.baidu.com",object : Callback{
            //失败后执行
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            //得到响应后执行
            override fun onResponse(call: Call, response: Response) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun showReponse(response:String){
        runOnUiThread {
            /*异步的封装
            public final void runOnUiThread(Runnable action) {
                if (Thread.currentThread() != mUiThread) {
                    mHandler.post(action);
                } else {
                    action.run();
                }
             }
             */
            binding.reponseText.text = response
        }
    }

    /**
     * 这个挂起函数实现：
     * 通过suspendCoroutine实现挂起当前协程，内部代码运行在一个普通的子线程中，
     * 之后在内部发起请求，并通过回调监听结果，
     * 请求成功：恢复协程，并返回响应数据作为函数返回值
     * 请求失败：恢复协程，同时传入具体的异常原因
     */
    suspend fun request(address:String):String{
        return suspendCoroutine { continuation ->
            HttpUtil.sendHttpRequest(address,object : HttpCallbackListener{
                //请求成功
                override fun onFinish(response: String) {
                    continuation.resume(response)
                }
                //请求失败
                override fun onError(e: Exception) {
                    continuation.resumeWithException(e)
                }
            })
        }
    }
}