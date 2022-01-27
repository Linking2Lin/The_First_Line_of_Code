package com.example.broadcasttest.off.line

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.broadcasttest.R
import com.example.broadcasttest.databinding.ActivityLogin2Binding

class LoginActivity : BaseActivity() {
    lateinit var binding: ActivityLogin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button5.setOnClickListener {
            val userName = binding.username.text.toString()
            val passWord = binding.password.text.toString()
            if (userName == "A" && passWord == "123456"){
                val intent = Intent(this,UIActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show()
            }
        }
    }
}