package com.example.retrofittest.callbacks

import com.example.networktest.callbacks.HttpCallbacklistener
import com.example.retrofittest.callbacks.Httputil
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun request(address:String):String{
    return suspendCoroutine { continuation -> //当前协程挂起，下面的代码放在普通线程中运行
        Httputil.sendHttpRequest(address,object:HttpCallbacklistener{
            override fun onFinnishi(response: String) {
                continuation.resume(response)
            }

            override fun onError(e: Exception) {
                continuation.resumeWithException(e)
            }
        })
    }
}