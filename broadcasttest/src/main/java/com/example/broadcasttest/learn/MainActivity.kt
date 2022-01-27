package com.example.broadcasttest.learn

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.broadcasttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var timeChangeReceiver: TimeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")//添加感兴趣的广播
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver,intentFilter)//注册接收器
        binding.button.setOnClickListener {
            val intent = Intent("MY_BROADCAST")//创建Intent对象，并添加广播内容
            intent.setPackage(packageName)//指定包名，使广播成为显式广播
            intent.putExtra("DA","DA JI BA")
            //sendBroadcast(intent)//发送广播
            sendOrderedBroadcast(intent,null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)//取消注册广播接收器
    }

    inner class TimeChangeReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context,"Time Changed",Toast.LENGTH_SHORT).show()
        }
    }
}