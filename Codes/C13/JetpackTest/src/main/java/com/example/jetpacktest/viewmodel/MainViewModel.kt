package com.example.jetpacktest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.jetpacktest.viewmodel.maps.`fun`.Repository
import com.example.jetpacktest.viewmodel.maps.`fun`.User

class MainViewModel(countReserved:Int):ViewModel() {
    //var counter = 0
    //var counter = countReserved

    //声明一个不可变的LiveData，get方法中返回_counter
    val counter:LiveData<Int>
        get() = _counter

    //该变量对外部不可见
    private val _counter = MutableLiveData<Int>()

    init {
        //确保值在初始化时可以恢复
        _counter.value = countReserved
    }

    fun plusOne(){
        val count = _counter.value ?:0
        _counter.value = count+1
    }

    fun clear(){
        _counter.value = 0
    }


    //----------------------------------------------------------------------------------------------
    //map的使用：
    private val userLiveData = MutableLiveData<User>()

    //将原始数据进行转化，map函数接收两个参数，第一个为数据源，第二个为转换操作
    //当userLiveData的数据发生变化时，map会监听到变化并执行转换函数中的逻辑，然后将转换后的数据通知给userName的观察者
    val userName:LiveData<String> = Transformations.map(userLiveData){
        "${it.firstName}   ${it.lastName}"
    }
    //----------------------------------------------------------------------------------------------
    //switchMap的使用
    /*
    fun getUser(userId:String):LiveData<User>{
        return Repository.getUser(userId)//每次返回的都是新的LiveData对象
    }
    */
    private val userIdLiveData = MutableLiveData<String>()

    val user:LiveData<User> = Transformations.switchMap(userIdLiveData){userId->
        Repository.getUser(userId)
    }
    fun getUser(userId:String){
        userIdLiveData.value = userId
    }
    //----------------------------------------------------------------------------------------------

}