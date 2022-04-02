package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.networktest.callbacks.HttpCallbacklistener
import com.example.networktest.callbacks.Httputil
import com.example.networktest.databinding.ActivityMainBinding
import com.example.networktest.gson.App
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONArray
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendRequest.setOnClickListener {
            //sendRequestWithHttpURLConnection()
            //sendRequestWithOkHttp
            //withCallbackHttpURLConnection()
            withCallbackHttpURLConnection()
        }
    }

    private fun sendRequestWithHttpURLConnection(){
        thread {
            var connection:HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.bing.com")
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
                showresponse(response.toString())
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
            /*

            //向服务器写数据:
            connection?.requestMethod = "POST"
            val output = DataOutputStream(connection?.outputStream)
            output.writeBytes("username=admain&password=123456")

             */
        }
    }

    private fun showresponse(str:String){
        runOnUiThread {
            binding.responseText.text = str
        }
    }

    private fun sendRequestWithOkHttp(){
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    //.url("http://10.0.2.2:7777/get_data.xml")
                    .url("http://10.0.2.2:7777/get_data.json")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData!= null){
                    //showresponse(responseData)
                    //parseXMLWithPull(responseData)
                    //parseJSONWithJSONObject(responseData)
                    parseJSONWithGSON(responseData)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun parseXMLWithPull(xmlData:String){
        try {
            val factory = XmlPullParserFactory.newInstance()//工厂实例
            val xmlPullParser = factory.newPullParser()//借助工厂实例获取解析器实例
            xmlPullParser.setInput(StringReader(xmlData))//将字符串以流的形式处理
            var eventType = xmlPullParser.eventType//获取当前解析事件
            var name = ""
            var id = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT){//当解析事件不是文档结束时循环
                val nodeName = xmlPullParser.name//获取当前节点名字
                when(eventType){
                    //开始解析某个节点
                    XmlPullParser.START_TAG ->{//节点开始
                        when(nodeName){
                            //如果节点名为以下之一，则调用nextText获取节点内具体内容
                            "id" ->id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG->{//节点结束
                        if ("app"==nodeName){//如果是app节点结束，则将数据进行输出
                            Log.d("XMLPULL", "parseXMLWithPull: $id")
                            Log.d("XMLPULL", "parseXMLWithPull: $name")
                            Log.d("XMLPULL", "parseXMLWithPull: $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()//指向下一个事件
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun parseJSONWithJSONObject(jsonData:String){
        try {
            val jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val version = jsonObject.getString("version")
                Log.d("数据: ", "parseJSONWithJSONObject: $id   $name   $version")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun parseJSONWithGSON(jsonData:String){
        val gson = Gson()
        val typeOf = object :TypeToken<List<App>>(){}.type
        val appList = gson.fromJson<List<App>>(jsonData,typeOf)

        for (app in appList){
            Log.d("数据", "parseJSONWithGSON: ${app.id}   ${app.name}   ${app.version}")
        }
    }

    private fun withCallbackHttpURLConnection(){
        Httputil.sendHttpRequest("http://10.0.2.2:7777/get_data.json",object : HttpCallbacklistener{
            override fun onFinnishi(response: String) {
                parseJSONWithGSON(response)
            }

            override fun onError(e: Exception) {
                e.printStackTrace()
            }

        })
    }

    private fun withCallbackOkHttp(){
        Httputil.sendPkHttpRequest("http://10.0.2.2:7777/get_data.json",object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val jsData = response.body?.string()
                if (jsData != null) {
                    parseJSONWithGSON(jsData)
                }
            }
        })
    }
}