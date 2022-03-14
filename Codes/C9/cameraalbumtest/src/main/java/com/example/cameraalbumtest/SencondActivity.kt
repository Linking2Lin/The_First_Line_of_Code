package com.example.cameraalbumtest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cameraalbumtest.databinding.ActivitySencondBinding

class SencondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySencondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySencondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().putExtra("value","I am back!"))
            finish()
        }
    }
}