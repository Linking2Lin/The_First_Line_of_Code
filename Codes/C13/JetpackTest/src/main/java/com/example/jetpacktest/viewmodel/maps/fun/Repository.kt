package com.example.jetpacktest.viewmodel.maps.`fun`

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpacktest.viewmodel.maps.`fun`.User

object Repository {
    fun getUser(userId:String):LiveData<User>{
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId,userId,0)
        return liveData
    }
}