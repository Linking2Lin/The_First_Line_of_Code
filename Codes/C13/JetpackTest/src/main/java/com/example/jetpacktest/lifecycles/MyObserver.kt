package com.example.jetpacktest.lifecycles

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver(val lifecycle:Lifecycle):LifecycleObserver {
    private val TAG = "MyObserver"


    @OnLifecycleEvent(Lifecycle.Event.ON_START)//给注解传入生命周期事件，事件类型共七种，分别对应Activity的7种生命周期，另外有一个ON_ANY,表示匹配Activity的任何生命周期回调
    fun activityStart(){
        Log.d(TAG, "activityStart: Start")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop(){
        Log.d(TAG, "activityStop: Stop")
    }
}