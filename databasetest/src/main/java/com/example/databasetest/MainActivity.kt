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

        val dbHelpter = MyDatabaseHelper(this,"BookStore.db",1)
        binding.create.setOnClickListener {
            dbHelpter.writableDatabase
        }
    }
}