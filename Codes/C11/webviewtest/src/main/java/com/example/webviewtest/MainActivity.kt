package com.example.webviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.webviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true//让WebView支持JavaScript脚本

        binding.webView.webViewClient = WebViewClient()//保证一个网页跳转到另一个网页时，目标网页仍然在当前WebView中显示

    }

    override fun onResume() {
        super.onResume()
        binding.webView.loadUrl("https://bing.com")//传入网址

    }
}