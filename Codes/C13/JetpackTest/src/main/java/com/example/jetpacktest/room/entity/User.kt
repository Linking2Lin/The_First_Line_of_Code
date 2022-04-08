package com.example.jetpacktest.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity//该注解表示该类是一个实体类
data class User(var firstName:String,var lastName:String,var age:Int){

    @PrimaryKey(autoGenerate = true)//该注解表示将id字段设为主键并自动生成
    var id:Long = 0
}
