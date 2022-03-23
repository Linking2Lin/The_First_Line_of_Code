package com.example.databasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.databasetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val dbHelper = MyDatabaseHelper(this,"BookStore.db",1)//将数据库名指定为BookStore.db，版本指定为1
        val dbHelper = MyDatabaseHelper(this,"BookStore.db",2)

        binding.createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

    }
}