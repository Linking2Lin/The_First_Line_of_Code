package com.example.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val TAG = "MyService"

    private val mBinder = DownloadBinder()

    class DownloadBinder:Binder(){
        fun startDownload(){
        }

        fun getProgress(): Int {
            return 0
        }
    }

    /**
     * Service内唯一的抽象方法，子类必须实现,应用程序通过返回的Binder对象与Service进行通信
     */
    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    /**
     * 创建时调用
     */
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    /**
     * 每次启动时调用
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 销毁时调用
     */
    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
}