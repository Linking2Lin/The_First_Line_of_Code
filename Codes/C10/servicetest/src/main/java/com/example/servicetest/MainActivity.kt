package com.example.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.servicetest.databinding.ActivityMainBinding
import com.example.servicetest.foreground.ForegroundService
import com.example.servicetest.intent.service.MyIntentService

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var downloadBinder:MyService.DownloadBinder

    private val connection = object:ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startService.setOnClickListener {
            //val intent = Intent(this,MyService::class.java)
            //val intent = Intent(this,ForegroundService::class.java)
            Log.d("Main", "onCreate: ${Thread.currentThread().name}")
            val intent = Intent(this,MyIntentService::class.java)
            startService(intent)
        }

        binding.stopService.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            stopService(intent)
        }

        binding.bindServicebtn.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            bindService(intent,connection,Context.BIND_AUTO_CREATE)
        }

        binding.unbindServiceBtn.setOnClickListener {
            unbindService(connection)
        }
    }
}