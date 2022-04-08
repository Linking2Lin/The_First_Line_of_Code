package com.example.jetpacktest.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jetpacktest.room.entity.Book

@Dao
interface IBookDao {

    @Insert
    fun insertBook(book: Book):Long

    @Query("select * from Book")
    fun loadAllBooks():List<Book>
}