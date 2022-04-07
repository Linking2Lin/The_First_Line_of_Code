package com.example.jetpacktest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(countReserved:Int):ViewModel() {
    //var counter = 0
    //var counter = countReserved

    val counter = MutableLiveData<Int>()

    init {
        //确保值在初始化时可以恢复
        counter.value = countReserved
    }

    fun plusOne(){
        val count = counter.value ?:0
        counter.value = count+1
    }

    fun clear(){
        counter.value = 0
    }
}