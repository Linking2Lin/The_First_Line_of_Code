package com.example.jetpacktest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Respository {

    fun getUser(userId:String):LiveData<TempData>{
        val liveData = MutableLiveData<TempData>()
        liveData.value = TempData(userId,18,"999")
        return liveData
    }
}