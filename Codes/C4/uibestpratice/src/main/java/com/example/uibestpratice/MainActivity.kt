package com.example.uibestpratice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uibestpratice.databinding.ActivityMainBinding
import com.example.uibestpratice.recyclerviews.tools.Message
import com.example.uibestpratice.recyclerviews.tools.MsgAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val msgList = ArrayList<Message>()
    private var adapter:MsgAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMsg()

        val layoutManager = LinearLayoutManager(this)
        adapter = MsgAdapter(msgList)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        binding.send.setOnClickListener {
            val content = binding.inputText.text.toString()
            if (content.isNotEmpty()){
                val msg = Message(content,Message.TYPE_SENT)
                msgList.add(msg)
                adapter?.notifyItemInserted(msgList.size-1)//有新消息时，刷新控件，
                binding.recyclerView.scrollToPosition(msgList.size-1)//控件定位到最后一行
                binding.inputText.setText("")

                val msgBack = Message(content.reversed(),Message.TYPE_RECEIVED)
                msgList.add(msgBack)
                adapter?.notifyItemInserted(msgList.size-1)
                binding.recyclerView.scrollToPosition(msgList.size-1)

            }
        }
    }

    private fun initMsg(){
        val msg1 = Message("Hello World",Message.TYPE_RECEIVED)
        msgList.add(msg1)
    }


}