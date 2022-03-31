package com.example.servicetest.kotlins.reifieds

import android.content.Context
import android.content.Intent

inline fun <reified T> startActivity(context: Context){
    val intent = Intent(context,T::class.java)
    context.startActivity(intent)
}

inline fun <reified T> startActivity(context: Context,block:Intent.()->Unit){//该函数增加了一个定义在Intent类中的函数类型参数
    val intent = Intent(context,T::class.java)
    intent.block()
    context.startActivity(intent)
}

inline fun <reified T> startService(context: Context){
    val intent = Intent(context,T::class.java)
    context.startService(intent)
}

inline fun <reified T> startService(context: Context,block:Intent.()->Unit){
    val intent = Intent(context,T::class.java)
    intent.block()
    context.startService(intent)
}