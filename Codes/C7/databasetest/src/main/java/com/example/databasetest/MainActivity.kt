package com.example.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.databasetest.databinding.ActivityMainBinding
import com.example.databasetest.high.`fun`.use.open
import java.lang.Exception
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private  val TAG = "MainActivity"

    @SuppressLint("Range")
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

            db.execSQL("insert into Book (author,price,name) values(?,?,?)", arrayOf("HHH",100,"2333"))
            db.execSQL("insert into Book (author,price,name) values(?,?,?)", arrayOf("HHHaaa",1000,"2333777"))



        }

        //更新数据
        binding.updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price",888)
            db.update("Book",values,"name=?", arrayOf("JI JI DA"))
            db.execSQL("update Book set price = ? where name = ?", arrayOf("777","2333"))
        }

        //删除shuju
        binding.deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            //db.delete("Book","pages > ?", arrayOf("777"))
            db.execSQL("delete from Book where name =?", arrayOf("JI JI"))
        }

        //查询数据
        binding.queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            //val cursor = db.query("Book",null,null,null,null,null,null)
            val cursor = db.rawQuery("select * from Book",null)
            Log.d(TAG, "onCreate: ${cursor.count}")
            if (cursor.moveToFirst()){
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val price = cursor.getInt(cursor.getColumnIndex("price"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    Log.d(TAG, "onCreate: 数据 $id  $author  $price  $name")
                }while (cursor.moveToNext())
            }
            cursor.close()
        }

        //事务
        binding.replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()//开启事务
            try {
                db.delete("Book",null,null)

                val values = ContentValues().apply {
                    put("author","DA JI BA")
                    put("price",777)
                    put("name","HHH")
                }
                db.insert("Book",null,values)
                db.setTransactionSuccessful()
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                db.endTransaction()
            }
        }

        //高阶的运用
        getSharedPreferences("data",Context.MODE_PRIVATE).open {
            putString("DA JI BA","JI BA DA DA DA ")
        }


    }
}