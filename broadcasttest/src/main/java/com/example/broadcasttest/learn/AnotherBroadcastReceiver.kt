package com.example.broadcasttest.learn

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AnotherBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context,"Received AnotherBroadcastReceiver ${intent.getStringExtra("DA")}", Toast.LENGTH_SHORT).show()
        abortBroadcast()

    }
}