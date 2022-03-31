package com.example.servicetest.kotlins.reifieds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.servicetest.MyService
import com.example.servicetest.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        startActivity<TestActivity>(this)

        startActivity<TestActivity>(this){
            putExtra("A",111)
            putExtra("B",222)
        }

        startService<MyService>(this)
        startService<MyService>(this){
            putExtra("C",9999)
        }
    }
}