package com.example.recyclerviewtest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Lin
 * @date 2022/3/24
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 * 适配器类，主构造函数用来将展示的数据源传递进来
 */
class FruitAdapter(private val fruitList:List<Fruit>):RecyclerView.Adapter<FruitViewHolder>() {

    //用来创建Holder实例，在该函数内加载子项布局并传入Holder的构造函数内并返回Holder实例
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item1,parent,false)
        val viewHolder = FruitViewHolder(view)

        //子项最外层注册点击,子项里没有注册点击的控件的点击事件会被外层捕获
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, fruit.name, Toast.LENGTH_SHORT).show()
        }
        //子项的ImageView注册点击
        viewHolder.fruitImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "HHH ${fruit.name}", Toast.LENGTH_SHORT).show()
        }

        return viewHolder
    }

    //用来对子项数据进行赋值，该函数在每个子项被滚动到屏幕内时执行
    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruit = fruitList[position]//通过位置参数得到当前实例
        //将数据设置到Holder对象内
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }

    //用来告诉RecyclerView一共有多少子项
    override fun getItemCount(): Int {
        return fruitList.size
    }

}