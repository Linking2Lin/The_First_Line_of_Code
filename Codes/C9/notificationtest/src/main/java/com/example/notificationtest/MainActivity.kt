package com.example.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.notificationtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager//创建一个通知管理器对通知进行管理，获取系统提供的即可
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){//8.0及以上启用
            //创建一个渠道，第一个参数为ID，需保证全局唯一，第二个为名称，供用户查看，需明确显示出该渠道的用途，第三个为重要等级，这个用户可以在系统设置里自行修改
            val channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
            //创建渠道
            manager.createNotificationChannel(channel)
            manager.cancel(1)//取消id为1的通知
        }

        binding.sendNotice.setOnClickListener {
            val intent = Intent(this,NotificationActivity::class.java)//创建意图
            val pi = PendingIntent.getActivity(this,0,intent,0)//构造出PendingIntent的实例
            //构造一个通知对象，使用AndroidX库中的API
            val notification = NotificationCompat.Builder(this,"normal")//第一个参数上下文，第二个为渠道ID，表明该通知位于哪个渠道，必须是已存在渠道ID之一
                .setContentTitle("ContentTitle")//指定通知标题内容
                .setContentText("ContentText")//指定通知正文内容
                .setSmallIcon(R.drawable.ic_launcher_background)//设置小图标，状态栏显示的那个，只能使用纯alpha图层的图片进行设置
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_foreground))//设置大图标，通知栏里面显示的那个图标
                .setContentIntent(pi)//给通知传入PendingIntent
                //.setAutoCancel(true)//设置自动消失
                .build()
            manager.notify(1,notification)//将通知交由管理器进行显示，第一个参数为通知的ID，需保证每个通知的ID都不相同，第二个为通知对象
        }

    }
}