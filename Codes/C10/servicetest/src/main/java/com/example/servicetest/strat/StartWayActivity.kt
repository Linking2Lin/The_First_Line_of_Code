package com.example.servicetest.strat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.servicetest.R
import com.example.servicetest.databinding.ActivityStartWayBinding

class StartWayActivity : AppCompatActivity() {
    lateinit var binding: ActivityStartWayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartWayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this,StartService::class.java)

        binding.button.setOnClickListener {
            startService(intent)
        }

        binding.button2.setOnClickListener {
            stopService(intent)
        }

    }
}