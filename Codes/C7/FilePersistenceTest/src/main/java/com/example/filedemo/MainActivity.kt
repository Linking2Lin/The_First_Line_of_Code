package com.example.filedemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.filedemo.databinding.ActivityMainBinding
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputText = load()
        if (inputText.isNotEmpty()){
            binding.editText.setText(inputText)
            binding.editText.setSelection(inputText.length)
            Toast.makeText(this, "Read Success", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = binding.editText.text.toString()
        save(inputText)
    }

    private fun save(inputText:String){
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val bufferedWriter = BufferedWriter(OutputStreamWriter(output))

            bufferedWriter.use { //扩展函数，保证Lambda表达式中的代码执行完毕后自动关闭外层流
                it.write(inputText)
            }
        }catch (e:Exception){
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
                    content.append(it)
                }
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
        return content.toString()
    }
}