package com.linking.the_first_line_of_code

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.linking.the_first_line_of_code.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("ai", "onCreate: " + this.opPackageName)

        binding.button.setOnClickListener {
//            val intent = Intent("DAJIJI")
//            intent.addCategory("JIJIDA")
//            startActivity(intent)
            val intent = Intent(this,SecondActivity::class.java).apply {
                putExtra("A",1)
                putExtra("B",2)
                putExtra("C",3)
                putExtra("D",4)
            }
            AlertDialog.Builder(this).apply {
                setTitle("This is a Dialog")
                setMessage("Something")
                setCancelable(false)
                setPositiveButton("确认"){
                    dialog,which->Toast.makeText(this@MainActivity,"${dialog.toString()}  $which",Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("返回"){
                    dialog,which->Toast.makeText(this@MainActivity,"${dialog.toString()}  $which",Toast.LENGTH_SHORT).show()

                }
                show()
            }
            //startActivity(intent)
        }
        if (savedInstanceState != null){
            val s = savedInstanceState.getString("DA")
            //Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
            Log.d("DAJIJI", "onCreate: $s")
        }
    }

    /**
     * 创建Menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)//调用父类的getMemuInflater()，获得一个MenuInflater对象，再调用inflate方法将menu填充到Activity中
        return true//表示允许创建的菜单显示出来
    }

    /**
     * 定义菜单响应事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add -> {
                val intent = Intent(Intent.ACTION_VIEW)//系统内置动作
                intent.data = Uri.parse("https://www.bing.com")
                startActivity(intent)
                Toast.makeText(this,"Click ADD",Toast.LENGTH_SHORT).show()
            }
            R.id.remove -> {
                Toast.makeText(this,"Click Remove",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    /**
     * 在Activity被系统回收前调用
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val str = "DA JI JI "
        outState.putString("DA",str)
        Log.d("DA", "onSaveInstanceState: $str")
    }
}