package com.linking.the_first_line_of_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//给当前Activity引入布局
        Toast.makeText(this,"Hello World",Toast.LENGTH_SHORT).show()
    }
}