package com.example.broadcastpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastpractice.activity.collector.BaseActivity
import com.example.broadcastpractice.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val intent = Intent("com.example.broadcastpratice.FORCE_OFFLINE")
            sendBroadcast(intent)
        }

    }
}