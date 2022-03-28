package com.example.recyclerviewtest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FruitViewHolder(view:View):RecyclerView.ViewHolder(view) {//主构造函数的View通常为RecyclerView子项的最外层布局
    //获取子项布局中的实例
    val fruitImage:ImageView = view.findViewById<ImageView>(R.id.fruitImage)
    val fruitName:TextView = view.findViewById<TextView>(R.id.fruitName)
}