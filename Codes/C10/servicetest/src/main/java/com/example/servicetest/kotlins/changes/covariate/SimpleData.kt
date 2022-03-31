package com.example.servicetest.kotlins.changes.covariate

class SimpleData<out T>(private val data:T?) {//out意味着T只能出现在out位置上
    //private var data:T ?=null

//    fun set(t:T?){
//        data=t
//    }

    fun get():T?{
        return data
    }
}