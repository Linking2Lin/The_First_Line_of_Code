package com.example.servicetest.bind

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class BindService : Service() {

    private var count:Int = 0
    private val myBinder = MyBinder()
    private var quit = true

    //一般Binder都是用内部类来实现
    inner class MyBinder:Binder(){
        fun getCount(): Int {
            return count
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }

    override fun onCreate() {
        super.onCreate()
        thread {
            while (quit){
                count++
                Log.d("count", "onCreate: $count")
                Thread.sleep(1000)
            }
        }
    }

    //被断开连接时执行
    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        quit = false
    }
}