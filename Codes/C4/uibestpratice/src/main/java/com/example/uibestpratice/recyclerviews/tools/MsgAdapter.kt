package com.example.uibestpratice.recyclerviews.tools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uibestpratice.R
import java.lang.IllegalArgumentException

/**
 * 适配器
 * @author Lin
 * @date 2022/3/28
 * ----------------------------------------------------
 * Is this the price I'm paying for my past mistakes? *
 * ----------------------------------------------------
 */
class MsgAdapter(private val msgList:List<Message>):RecyclerView.Adapter<MsgViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder
    = if (viewType == Message.TYPE_RECEIVED){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false)
        LeftViewHolder(view)
    }else{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false)
        RightViewHolder(view)
    }

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msg = msgList[position]
        when(holder){
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
            //else -> throw IllegalArgumentException() 使用密封类将Holder进行修改后，该语句可以优化
        }
    }

    //获取数据源长度
    override fun getItemCount() = msgList.size

    //获取子项类型
    override fun getItemViewType(position: Int): Int {
        return msgList[position].type
    }
}