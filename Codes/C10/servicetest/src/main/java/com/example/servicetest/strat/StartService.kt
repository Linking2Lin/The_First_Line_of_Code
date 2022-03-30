package com.example.servicetest.strat

import android.app.Service
import android.content.Intent
import android.os.IBinder

class StartService : Service() {

    //被绑定时回调
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    //被创建时回调
    override fun onCreate() {
        super.onCreate()
    }

    //被启动时回调
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    //被关闭前回调
    override fun onDestroy() {
        super.onDestroy()
    }
}