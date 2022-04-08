package com.example.highkills

import android.widget.Toast

fun String.showToast(duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(MyApplication.context, this, Toast.LENGTH_SHORT).show()
}