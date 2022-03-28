package com.example.uibestpratice.recyclerviews.tools
/**
 * 消息的实体类
 * @author Lin
 * @date 2022/3/28
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
class Message(val content:String,val type:Int) {
    companion object{
        const val TYPE_SENT = 1
        const val TYPE_RECEIVED = 0
    }
}