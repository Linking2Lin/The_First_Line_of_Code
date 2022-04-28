package com.example.jetpacktest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel(countReserved:Int) : ViewModel() {

                  //可变的LiveData对象，里面包含的是整型数据
    var counter = MutableLiveData<Int>()


    //----------------------------------------------------------------------------------------------
    val counterAfter:LiveData<Int>//不可变的Livedata，用来向外部暴露数据
            get() = _counterAfter//当外部调用该变量的get方法时，会返回_counterAfter

    private val _counterAfter = MutableLiveData<Int>()

    //初始化时将值设置为保存的值
    init {
        counter.value = countReserved

        _counterAfter.value = countReserved
    }

    //map的用法：
    private val tempLiveData = MutableLiveData<TempData>()
                                                        //原始对象
    val tempUser:LiveData<String> = Transformations.map(tempLiveData){temp->
        //转换行为
        "${temp.name}   ${temp.age}"//将原始对象的一部分属性提取出来转换为字符串对象赋给tempUser
    }
    //当tempLiveData发生改变时，map会监听到变化并执行逻辑导致tempUser同时发生改变，所以外部观察tempUser即可

    //switchMap:

    private val userLiveData = MutableLiveData<String>()

    //2.userLiveData中数据发生变化，观察它的switchMap会立即执行，
    // 调用Respository.getUser(user)获得真正的用户数据，
    // 同时将获得到的包含用户数据的LiveData转为user这个可观察的Livedata，因此，对于Activity来说，只要观察user这个liveData即可
    val user:LiveData<TempData> = Transformations.switchMap(userLiveData){user->
        Respository.getUser(user)
    }

    //1. 外部调用该方法，将userID设置到userLiveData中，
    fun getUser(userID:String){
        userLiveData.value = userID
    }


    //----------------------------------------------------------------------------------------------



    fun plusOne(){
        val count = counter.value ?: 0
        counter.value = count + 1

        val countAfter = _counterAfter.value ?: 0 //value为空返回0，不为空返回value
        _counterAfter.value = countAfter + 1
    }

    fun clear(){
        counter.value = 0
    }

}