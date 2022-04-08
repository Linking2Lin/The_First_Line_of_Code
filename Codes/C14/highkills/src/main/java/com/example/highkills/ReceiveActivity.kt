package com.example.highkills

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.highkills.trans.SeWay

class ReceiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive)

        val GetSe = intent.getSerializableExtra("A") as SeWay
    }
}