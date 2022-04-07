package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacktest.databinding.ActivityMainBinding
import com.example.jetpacktest.lifecycles.MyObserver
import com.example.jetpacktest.viewmodel.MainViewModel
import com.example.jetpacktest.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var sp:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("last",0)
        Log.d("读取值", "onCreate: $countReserved")
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)


        binding.button.setOnClickListener {
           viewModel.plusOne()
            /*
            viewModel.counter++
            refreshCounter()

             */
        }
        binding.clear.setOnClickListener {
            viewModel.clear()
        /*
            viewModel.counter = 0
            refreshCounter()

             */
        }
        //refreshCounter()

        viewModel.counter.observe(this) { count ->
            binding.infoText.text = count.toString()
        }

        lifecycle.addObserver(MyObserver(lifecycle))
    }

    private fun refreshCounter(){
        binding.infoText.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            //putInt("last",viewModel.counter)
            putInt("last",viewModel.counter.value?:0)
        }
        Log.d("保存值", "onPause: ${viewModel.counter}")
        Log.d("当前", "onPause: ${lifecycle.currentState}")
    }
}