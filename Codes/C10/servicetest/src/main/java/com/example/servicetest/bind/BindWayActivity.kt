package com.example.servicetest.bind

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.example.servicetest.databinding.ActivityBindWayBinding

class BindWayActivity : AppCompatActivity(){
    lateinit var binding: ActivityBindWayBinding

    lateinit var binder:BindService.MyBinder

    val con = object :ServiceConnection{
        //连接成功时调用
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = service as BindService.MyBinder
        }

        //断开连接时调用
        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBindWayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this,BindService::class.java)
        binding.bind.setOnClickListener {
            bindService(intent,con,Service.BIND_AUTO_CREATE)
        }
        binding.unBind.setOnClickListener {
            unbindService(con)
        }
        binding.getServiceStatus.setOnClickListener {
            Toast.makeText(this, "当前 ${binder.getCount()}", Toast.LENGTH_SHORT).show()
        }
    }
}