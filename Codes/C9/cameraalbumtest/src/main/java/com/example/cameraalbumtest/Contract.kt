package com.example.cameraalbumtest

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class Contract: ActivityResultContract<String,String>() {//第一个为输入的类型，第二个为输出的类型
    /**
     * Create an intent that can be used for [android.app.Activity.startActivityForResult].
     */
    override fun createIntent(context: Context, input: String): Intent {
        return Intent(context,SencondActivity::class.java).apply {
            putExtra("name",input)
        }
    }

    /**
     * Convert result obtained from [android.app.Activity.onActivityResult] to [O].
     */
    override fun parseResult(resultCode: Int, intent: Intent?): String {
        val data = intent?.getStringExtra("result")
        return if (resultCode == Activity.RESULT_OK && data != null) data else "HHH"
    }

}