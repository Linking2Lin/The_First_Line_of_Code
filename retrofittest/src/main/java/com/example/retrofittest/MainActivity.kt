package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofittest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getAppDataBtn.setOnClickListener {
            val retrofit = Retrofit.Builder()//构建对象
                .baseUrl("http://10.0.2.2:7777/")//指定请求根路径
                .addConverterFactory(GsonConverterFactory.create())//指定解析数据时使用的转换库
                .build()
            val appService = retrofit.create(AppService::class.java)
            appService.getAppData().enqueue(object : Callback<List<App>> {
                /**
                 * Invoked for a received HTTP response.
                 *
                 *
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call [Response.isSuccessful] to determine if the response indicates success.
                 */
                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                    val list = response.body()
                    if (list!=null){
                        for (app in list){
                            Log.d(TAG, "onResponse: id is ${app.id}")
                            Log.d(TAG, "onResponse: name is ${app.name}")
                            Log.d(TAG, "onResponse: version is ${app.version}")
                        }
                    }
                }

                /**
                 * Invoked when a network exception occurred talking to the server or when an unexpected exception
                 * occurred creating the request or processing the response.
                 */
                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }
    }
}