package com.example.uibestpratice.recyclerviews.tools

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uibestpratice.R

class RightViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val rightMsg: TextView = view.findViewById<TextView>(R.id.rightMsg)
}