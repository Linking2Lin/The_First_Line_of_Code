package com.example.databasetest.high.`fun`.use

import android.content.SharedPreferences

//向SharedPreferences类中添加一个open的高阶函数，该函数接受SharedPreferences.Editor函数类型并无返回类型
fun SharedPreferences.open(block:SharedPreferences.Editor.()-> Unit){
    val editor = edit()
    editor.block()
    editor.apply()
}

