package com.example.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.databasetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelpter = MyDatabaseHelper(this,"BookStore.db",2)
        binding.create.setOnClickListener {
            dbHelpter.writableDatabase
        }

        binding.Add.setOnClickListener {
            val db = dbHelpter.writableDatabase
            val values1 = ContentValues().apply {
                put("name","A")
                put("author","B")
                put("page",77)
                put("price",17)
            }
            db.insert("Book",null,values1)
            db.execSQL("insert into Book (name,author,pages,price) values(?,?,?,?)", arrayOf("DA","JIBA","100","50"))
        }

        binding.qurey.setOnClickListener {
            val db = dbHelpter.writableDatabase
            val cursor = db.query("Book",null,null,null,null,null,null)
            if (cursor.moveToFirst()){
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getString(cursor.getColumnIndex("pages"))
                    val price  = cursor.getString(cursor.getColumnIndex("price"))

                    Log.d(TAG, "onCreate: $name")
                    Log.d(TAG, "onCreate: $author")
                    Log.d(TAG, "onCreate: $pages")
                    Log.d(TAG, "onCreate: $price")
                }while (cursor.moveToNext())
            }
            cursor.close()
        }
    }
}