package com.example.jetpacktest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(countReserved:Int) : ViewModel() {

                  //可变的LiveData对象，里面包含的是整型数据
    var counter = MutableLiveData<Int>()

    init {
        counter.value = countReserved
    }

    fun plusOne(){
        val count = counter.value ?: 0
        counter.value = count+1
    }

    fun clear(){
        counter.value = 0
    }
}