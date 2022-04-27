package com.example.jetpacktest.viewmodel

import android.widget.ViewSwitcher.ViewFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val countReserved:Int):ViewModelProvider.Factory {

    //创建MainViewModel实例并将参数传递进去
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}