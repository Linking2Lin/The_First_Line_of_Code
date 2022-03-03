package com.example.filepersistencetest

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    private fun save(input:String){
        try {
            val output = openFileOutput("data",Context.MODE_PRIVATE)//以覆盖的形式向data内写入数据
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                //use:一个内置扩展函数，保证在Lambda表达式中的代码全部执行完毕后自动将外层的流关闭
                it.write(input)
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }
    private fun load():String{
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    //forEachLine：将读到的每行内容回调到表达式中
                    content.append(it)
                }
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
        return content.toString()
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveSharedPrefences(context: Context){
        val sharedPreferences = context.getSharedPreferences("data", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("JIJI",100)
        editor.apply()
    }
}
