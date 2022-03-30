package com.example.databasetest
/**
 * @author Lin
 * @date 2022/3/23
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(
    val context: Context,
    name: String,
    version: Int,
) : SQLiteOpenHelper(
    context,
    name,
    null,
    version
) {

    private val createBook = "create table Book("+
            "id integer primary key autoincrement,"+
            "author text,"+
            "price real,"+
            "name text)"

    private val createCategory = "create table Category("+
            "id integer primary key autoincrement,"+
            "category_name text,"+
            "category_code integer)"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        //Toast.makeText(context, "创建数据库成功", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Book")
        db?.execSQL("drop table if exists Category")
        onCreate(db)
    }
}