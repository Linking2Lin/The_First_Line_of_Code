package com.example.broadcasttest.off.line

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class offlineReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (context != null) {
                AlertDialog.Builder(context).apply {
                    setTitle("Warring")
                    setMessage("Your count is login in another device")
                    setCancelable(false)
                    setPositiveButton("OK"){_,_ ->
                        ActivityCollector.finishAll()//销毁所有
                        val i = Intent(context,LoginActivity::class.java)//构建启动登录界面的意图
                        context.startActivity(i)//启动登录界面

                    }
                }
            }
        }
    }
}