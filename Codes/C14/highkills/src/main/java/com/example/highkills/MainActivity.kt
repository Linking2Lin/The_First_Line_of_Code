package com.example.highkills

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.highkills.databinding.ActivityMainBinding
import com.example.highkills.trans.SeWay

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        "DA JI BA".showToast()

        val seWay = SeWay()
        val intentSe = Intent(this,ReceiveActivity::class.java)
        intentSe.putExtra("A",seWay)//将对象放入
        startActivity(intentSe)
    }
}