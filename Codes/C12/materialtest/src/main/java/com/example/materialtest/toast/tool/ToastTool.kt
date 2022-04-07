package com.example.materialtest.toast.tool

import android.content.Context
import android.widget.Toast

fun String.showToast(context: Context,time:Int = Toast.LENGTH_SHORT){
    Toast.makeText(context, this, time).show()
}

fun Int.showToast(context: Context){
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}