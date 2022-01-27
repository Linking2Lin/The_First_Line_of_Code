package com.example.broadcasttest.learn

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context,"Received MyBroadcast ${intent.getStringExtra("DA")}",Toast.LENGTH_SHORT).show()
        abortBroadcast()
    }
}