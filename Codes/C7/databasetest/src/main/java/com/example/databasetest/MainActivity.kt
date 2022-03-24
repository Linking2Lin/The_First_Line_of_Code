package com.example.databasetest

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.databasetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val dbHelper = MyDatabaseHelper(this,"BookStore.db",1)//将数据库名指定为BookStore.db，版本指定为1
        val dbHelper = MyDatabaseHelper(this,"BookStore.db",3)
        //创建/更新数据库
        binding.createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        //添加数据
        binding.addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1:ContentValues = ContentValues().apply {
                put("name","DA JI JI")
                put("author","JI JI DA")
                //put("pages",777),
                put("price",77)
            }
            db.insert("Book",null,values1)
            val values2:ContentValues = ContentValues().apply {
                put("name","JI JI")
                put("author","JI DA")
                //put("pages",888)
                put("price",999)
            }
            db.insert("Book",null,values2)
            Toast.makeText(this, "添加数据成功", Toast.LENGTH_SHORT).show()
        }

        //更新数据
        binding.updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price",888)
            db.update("Book",values,"name=?", arrayOf("JI JI DA"))
        }

    }
}