package com.example.broadcasttest.off.line

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcasttest.R
import com.example.broadcasttest.databinding.ActivityUiactivityBinding

class UIActivity : BaseActivity() {
    lateinit var binding: ActivityUiactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.offLine.setOnClickListener {
            val intent = Intent("com.example.broadcasttest.OFFLINE")
            sendBroadcast(intent)
        }
    }
}