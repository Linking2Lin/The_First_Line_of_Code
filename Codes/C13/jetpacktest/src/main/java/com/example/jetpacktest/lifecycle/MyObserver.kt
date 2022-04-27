package com.example.jetpacktest.lifecycle

import android.util.Log
import androidx.lifecycle.*


class MyObserver(private val lifecycle:Lifecycle) : DefaultLifecycleObserver {

    private val TAG = "MyObserver"

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.d(TAG, "onCreate: ${lifecycle.currentState}")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.d(TAG, "onPause: 获取当前状态为 ${lifecycle.currentState}")

    }


}
