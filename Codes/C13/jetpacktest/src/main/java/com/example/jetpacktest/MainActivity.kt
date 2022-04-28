package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacktest.databinding.ActivityMainBinding
import com.example.jetpacktest.lifecycle.MyObserver
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
        //-----------------------------------------------------------------------------------------
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("ce",0)

        viewModel = ViewModelProvider(this,MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        //-----------------------------------------------------------------------------------------

        //不能直接创建Viewmodel的实例，必须通过ViewModelProvider来获取viewModel的实例
        //viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //用来观察数据的变化，当观察的数据发生改变时，会回调到Lambda表达式中
        viewModel.counterAfter.observe(this) { count ->
            binding.textView.text = count.toString()
        }

        binding.button.setOnClickListener {
            viewModel.plusOne()
            //refreshCounter()
        }
        //refreshCounter()
        binding.getUser.setOnClickListener {
            val userId = (0..1000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.user.observe(this){ user->
            binding.textView.text = user.name
        }

        lifecycle.addObserver(MyObserver(lifecycle))
    }

    private fun refreshCounter(){
        binding.textView.text = viewModel.counter.toString()
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}