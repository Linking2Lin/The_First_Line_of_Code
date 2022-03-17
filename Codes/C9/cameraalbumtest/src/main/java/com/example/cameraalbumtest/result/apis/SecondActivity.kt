package com.example.cameraalbumtest.result.apis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cameraalbumtest.R
import com.example.cameraalbumtest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data","data from SecondActivity")//将数据封装到intent对象内
            setResult(RESULT_OK,intent)//将返回的数据设置结果码并返回

            finish()
        }
    }

}