package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFruits()//初始化数据源
        /*
        val layoutManager = LinearLayoutManager(this)//准备布局管理器
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL//设置布局排列方向为横向
        */
        val layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)//第一个参数指定列数，第二个指定排列方向
        binding.recyclerView.layoutManager = layoutManager//将布局管理器设置到控件中
        val adapter = FruitAdapter(fruitList)//创建适配器实例并将数据传入
        binding.recyclerView.adapter = adapter//将适配器设置到控件上
    }

    /**
     * 初始化数据
     */
    private fun initFruits(){
        repeat(2){
            fruitList.add(Fruit("Apple".getRandomLengthString(),R.drawable.apple_pic))
            fruitList.add(Fruit("Banana".getRandomLengthString(),R.drawable.banana_pic))
            fruitList.add(Fruit("Orange".getRandomLengthString(),R.drawable.orange_pic))
            fruitList.add(Fruit("Watermelon".getRandomLengthString(),R.drawable.watermelon_pic))
            fruitList.add(Fruit("Pear".getRandomLengthString(),R.drawable.pear_pic))
            fruitList.add(Fruit("Grape".getRandomLengthString(),R.drawable.grape_pic))
            fruitList.add(Fruit("Pineapple".getRandomLengthString(),R.drawable.pineapple_pic))
            fruitList.add(Fruit("Strawberry".getRandomLengthString(),R.drawable.strawberry_pic))
            fruitList.add(Fruit("Cherry".getRandomLengthString(),R.drawable.cherry_pic))
            fruitList.add(Fruit("Mango".getRandomLengthString(),R.drawable.mango_pic))
        }
    }

    private fun String.getRandomLengthString():String{
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n){
            builder.append(this)
        }
        return builder.toString()
    }
}