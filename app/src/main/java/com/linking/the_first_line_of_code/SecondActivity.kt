package com.linking.the_first_line_of_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}