package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.jetpacktest.databinding.ActivityMainBinding
import com.example.jetpacktest.lifecycles.MyObserver
import com.example.jetpacktest.room.database.AppDatabase
import com.example.jetpacktest.room.entity.User
import com.example.jetpacktest.viewmodel.MainViewModel
import com.example.jetpacktest.viewmodel.MainViewModelFactory
import com.example.jetpacktest.workmanager.SimpleWork
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var sp:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //------------------------------------------------------------------------------------------

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
            //binding.infoText.text = count.toString()

        }

        lifecycle.addObserver(MyObserver(lifecycle))


        binding.getUserBtn.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }
        viewModel.user.observe(this){user->
            binding.infoText.text = user.firstName
        }
        //------------------------------------------------------------------------------------------

        val userDao = AppDatabase.getDatabase(this).userDao()

        val user1 = User("Tom","Brady",77)
        val user2 = User("Tom","Lin",177)

        binding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        binding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 99
                userDao.updateUser(user1)
            }
        }

        binding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Brady")
            }
        }

        binding.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("MainActivity", "${user.toString()} ")
                }
                Log.d("HHH", "onCreate: user 结束")


            }
        }

        binding.doWorkBtn.setOnClickListener {
            val request1 = OneTimeWorkRequest.Builder(SimpleWork::class.java).build()
            val request2 = PeriodicWorkRequest.Builder(SimpleWork::class.java,15, TimeUnit.MINUTES).build()

            WorkManager.getInstance(this).enqueue(request1)
            WorkManager.getInstance(this).enqueue(request2)
        }

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


    private fun refreshCounter(){
        binding.infoText.text = viewModel.counter.toString()
    }

}