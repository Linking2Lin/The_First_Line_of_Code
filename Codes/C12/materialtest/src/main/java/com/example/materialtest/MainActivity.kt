package com.example.materialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.materialtest.databinding.ActivityMainBinding
import com.example.materialtest.recycler.tools.Fruit
import com.example.materialtest.recycler.tools.FruitAdapter
import com.example.materialtest.toast.tool.showToast
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var fruits = mutableListOf(
        Fruit("Apple",R.drawable.apple),
        Fruit("Banana",R.drawable.banana),
        Fruit("Orange",R.drawable.orange),
        Fruit("Watermelon",R.drawable.watermelon),
        Fruit("Pear",R.drawable.pear),
        Fruit("Grape",R.drawable.grape),
        Fruit("Pineapple",R.drawable.pineapple),
        Fruit("Strawberry",R.drawable.strawberry),
        Fruit("Cherry",R.drawable.cherry),
        Fruit("Mango",R.drawable.mango)
    )
    var fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        inintFruits()
        val layoutManager = GridLayoutManager(this,2)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(this,fruitList)
        binding.recyclerView.adapter = adapter

        //设置下拉进度条的颜色
        binding.swipeRefresh.setColorSchemeColors(resources.getColor(R.color.purple_500))
        binding.swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            //it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        binding.navView.setCheckedItem(R.id.navCall)
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navCall -> Toast.makeText(this, "Call", Toast.LENGTH_SHORT).show()

            }
            binding.drawerLayout.closeDrawers()
            true
        }

        binding.fab.setOnClickListener {
            //Toast.makeText(this, "HHH Click", Toast.LENGTH_SHORT).show()
            Snackbar.make(it,"Data delete",Snackbar.LENGTH_SHORT)
                .setAction("Undo"){
                    //Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                    "Data Restored".showToast(this)
                }
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.backop -> Toast.makeText(this, "Back UP", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            android.R.id.home -> binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    private fun inintFruits(){
        fruitList.clear()
        repeat(77){
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    private fun refreshFruits(adapter:FruitAdapter){
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                inintFruits()
                adapter.notifyDataSetChanged()
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }
}