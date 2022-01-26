package com.linking.the_first_line_of_code

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 基类，用来给各Activity继承，方便展示当前Activity名
 */
open class BaseActivity:AppCompatActivity() {
    private val TAG = "BaseActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: " + javaClass.simpleName)
    }
}