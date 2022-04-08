package com.example.jetpacktest.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jetpacktest.room.dao.IBookDao
import com.example.jetpacktest.room.dao.IUserDao
import com.example.jetpacktest.room.entity.Book
import com.example.jetpacktest.room.entity.User

@Database(version = 3, entities = [User::class,Book::class])//声明数据库的版本号以及包含哪些实体类，多个实体类之间用逗号隔开
abstract class AppDatabase:RoomDatabase(){
    abstract fun userDao():IUserDao
    abstract fun bookDao():IBookDao

    //使用单例模式来生成AppDatabase的实例，因为全局中只应存在一个AppDatabase的实例
    companion object{

        val MIGATION_1_2 = object :Migration(1,2){//实现Migration匿名类，传入1，2这两参数，表示当数据库版本从1升级到2时执行该匿名类中的升级逻辑
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book(id integer primary key autoincrement not null,name text not null,pages integer not null)")//建表语句
            }
        }

        val MIGATION_2_3 = object :Migration(2,3){//从2升级到3
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        private var instance:AppDatabase?=null

        @Synchronized
        fun getDatabase(context: Context):AppDatabase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"app_database")
                .addMigrations(MIGATION_1_2, MIGATION_2_3)
                .build().apply {
                instance = this
            }
        }
    }
}