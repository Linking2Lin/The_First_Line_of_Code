package com.example.networktest.callback

/**
 * 回调接口
 * @author Lin
 * @date 2022/4/26
 * ----------------------------------------------------
 * Tell me God,are you punishing me?
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
interface HttpCallbackListener {

    /**
     * 当服务器成功响应时调用
     */
    fun onFinish(response:String)

    /**
     * 当网络操作出错时调用
     */
    fun onError(e:Exception)
}