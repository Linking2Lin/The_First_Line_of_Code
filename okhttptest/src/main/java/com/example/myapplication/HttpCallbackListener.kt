package com.example.myapplication

import java.lang.Exception

interface HttpCallbackListener {
    /**
     * 服务器成功响应时调用
     */
    fun onFinish(response:String)

    /**
     * 网络操作出现错误时调用
     */
    fun onError(e:Exception)
}