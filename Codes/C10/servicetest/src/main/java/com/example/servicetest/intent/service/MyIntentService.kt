package com.example.servicetest.intent.service

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyIntentService: IntentService("name") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("name", "onHandleIntent: ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("name", "onDestroy: is destroy ")
    }
}