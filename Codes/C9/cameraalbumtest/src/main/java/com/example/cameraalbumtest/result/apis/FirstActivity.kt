package com.example.cameraalbumtest.result.apis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cameraalbumtest.R
import com.example.cameraalbumtest.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private val TAG = "FirstActivity"
    lateinit var binding: ActivityFirstBinding

    /**
     * 制定协议，以进行携带数据的界面跳转
     * registerForActivityResult，该方法用来注册协议，第一个参数接收协议，可以是API内的协议，也可以是自定义的协议，第二个参数为一个回调，用来处理返回的数据
     */
    private val requestDataLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        //result对象为上一个Activity返回的数据对象
        if (result.resultCode == RESULT_OK){
            //获取返回的数据
            val data = result.data?.getStringExtra("data")
            //处理返回的数据
            Log.d(TAG, "返回来的数据: $data")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            requestDataLauncher.launch(intent)
        }
    }


}