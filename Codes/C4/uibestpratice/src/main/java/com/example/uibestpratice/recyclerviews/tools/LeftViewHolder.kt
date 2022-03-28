package com.example.uibestpratice.recyclerviews.tools

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uibestpratice.R

class LeftViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val leftMsg: TextView = view.findViewById<TextView>(R.id.leftMsg)
}