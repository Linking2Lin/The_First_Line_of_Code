package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.gson.App
import com.example.myapplication.sax.ContentHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.ByteArrayInputStream
import java.io.StringReader
import java.lang.Exception
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendRequest.setOnClickListener {
            Toast.makeText(this,"DAJIBA",Toast.LENGTH_SHORT).show()
            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithOkHttp(){
        thread {
            try {
                /*
                //发起request请求
                val client = OkHttpClient()//创建一个OkHttpClient实例
                val request = Request.Builder().url("http://10.0.2.2:7777/get_data.xml").build()//创建一个Request对象并指定url
                val response = client.newCall(request).execute()//创建一个Call对象发送请求并获取服务器返回的数据
                val responseData = response.body?.string()//将返回的数据转为字符串


                //发起post请求
                val requestBody = FormBody.Builder().add("username","DAJIBA").add("password","DAJIJI").build()//构建RequestBody来存放待提交的参数
                val request = Request.Builder().url("https://www.bing.com").post(requestBody).build()//
                val response = client.newCall(request).execute()//发送并获取返回的数据
                */
                val client = OkHttpClient()
                //val request = Request.Builder().url("http://10.0.2.2:7777/get_data.xml").build()//10.0.2.2对于模拟器是计算机本身的IP地址
                val request = Request.Builder().url("http://10.0.2.2:7777/get_data.json").build()//10.0.2.2对于模拟器是计算机本身的IP地址
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()

                if (responseData != null) {
                    showResponse(responseData)
                    //parseXMLWithPull(responseData)
                    //parseXMLWithSAX(responseData)
                    //parseJSONWithJSONObject(responseData)
                    parseJSONWithGSON(responseData)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    private fun showResponse(response:String){
        runOnUiThread {
            binding.textview.text = response
        }
    }

    private fun parseXMLWithPull(xmlData:String): Unit {
        //val input:ByteArrayInputStream = ByteArrayInputStream(xmlData.body?.bytes())
        try {
            //Log.d(TAG, "parseXMLWithPull: 获取的数据为：$xmlData")
            val factory = XmlPullParserFactory.newInstance()//工厂实例，用来获取解析器实例
            val xmlPullParser = factory.newPullParser()//获得解析器对象
            //val input:ByteArrayInputStream = ByteArrayInputStream(xmlData.body?.bytes())
            xmlPullParser.setInput(StringReader(xmlData))//将数据输入到解析器
            var eventType = xmlPullParser.eventType//指向当前解析器事件
            var id = ""
            var name = ""
            var version =  ""
            while (eventType != XmlPullParser.END_DOCUMENT){//直到解析到文档结束
                val nodeName = xmlPullParser.name
                when(eventType){
                    XmlPullParser.START_TAG -> {
                        when(nodeName){
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG ->{
                        if ("app" == nodeName){
                            Log.d(TAG, "parseXMLWithPull: id is $id")
                            Log.d(TAG, "parseXMLWithPull: name is $name")
                            Log.d(TAG, "parseXMLWithPull: version is $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            //input.close()
        }
    }

    private fun parseXMLWithSAX(xmlData:String): Unit {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()
            //将实例设置到XMLReader中
            xmlReader.contentHandler = handler
            xmlReader.parse(InputSource(StringReader(xmlData)))
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun parseJSONWithJSONObject(jsonData:String){
        try {
            //文件中是一个数组，将服务器返回的数据放入JSONArray对象中
            val jsonArray = JSONArray(jsonData)
            //遍历JSONArray对象
            for (i in 0 until jsonArray.length()){
                //取出数组中的JSONObject对象
                val jsonObject = jsonArray.getJSONObject(i)
                //输出对象中包含的信息
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val version = jsonObject.getString("version")

                Log.d(TAG, "parseJSONWithJSONObject: id is $id")
                Log.d(TAG, "parseJSONWithJSONObject: name is $name")
                Log.d(TAG, "parseJSONWithJSONObject: version is $version")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun parseJSONWithGSON(jsonData:String){
        val gson = Gson()
        val typeOf = object : TypeToken<List<App>>(){}.type
        val appList = gson.fromJson<List<App>>(jsonData,typeOf)
        for (app in appList){
            Log.d(TAG, "parseJSONWithGSON: id is ${app.id}")
            Log.d(TAG, "parseJSONWithGSON: name is ${app.name}")
            Log.d(TAG, "parseJSONWithGSON: version is ${app.version}")
        }
    }
}