package com.example.materialtest.snackbar.tool

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.time.Duration

fun View.showSnackbar(text:String,actionText:String?= null,duration: Int = Snackbar.LENGTH_SHORT,block:(()->Unit)?=null){
    val snackbar = Snackbar.make(this,text,duration)
    if (actionText!=null && block!=null){
        snackbar.setAction(actionText){
            block()
        }
    }
    snackbar.show()
}