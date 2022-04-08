package com.example.jetpacktest.room.dao

import androidx.room.*
import com.example.jetpacktest.room.entity.User

@Dao//声明该接口是Dao
interface IUserDao {

    @Insert//表示将参数中的user对象插入数据库中，返回值为生成的主键id
    fun insertUser(user: User):Long

    @Update//表示将参数中的User对象更新到数据库中
    fun updateUser(newUser:User)

    @Query("select * from User")
    fun loadAllUsers():List<User>

    @Query("select*from User where age > :age")
    fun loadUsersOlderThan(age:Int):List<User>

    @Delete//将传入的User对象从数据库中删除
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName:String):Int
}