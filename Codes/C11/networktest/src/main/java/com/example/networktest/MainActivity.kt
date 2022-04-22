package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.networktest.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

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
}