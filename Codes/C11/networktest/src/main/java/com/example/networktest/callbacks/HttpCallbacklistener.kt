package com.example.networktest.callbacks

interface HttpCallbacklistener {
    /**
     * 服务器成功响应请求时调用
     */
    fun onFinnishi(response:String)

    /**
     * 当出现错误时调用
     */
    fun onError(e:Exception)
}