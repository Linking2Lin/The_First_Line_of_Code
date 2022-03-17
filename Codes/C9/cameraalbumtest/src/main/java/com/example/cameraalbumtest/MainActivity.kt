package com.example.cameraalbumtest

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.cameraalbumtest.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    private val takePhoto = 1
    private val fromeAlbum = 2
    lateinit var imageUri:Uri
    lateinit var outputImage:File


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //拍照
        binding.button.setOnClickListener {
            //创建File对象，用来储存拍照后的图片
            outputImage = File(externalCacheDir,"output_image.jpg")//在缓存内创建一个File对象来放置摄像头拍下的照片
            if (outputImage.exists()){
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){//运行系统版本是否高于于7.0，7.0后直接使用本地真实路径的Uri被认为是不安全的
                FileProvider.getUriForFile(this,"com.example.cameraalbumtest.fileprovider",outputImage)//将File对象转为封装过的Uri对象
            }else{
                Uri.fromFile(outputImage)
            }
            //启动相机
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            startActivityForResult(intent,takePhoto)
        }
        //选择照片
        binding.changePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)//打开系统文件选择器
            intent.addCategory(Intent.CATEGORY_OPENABLE)//设置过滤，只打开图片文件
            //指定只显示图片
            intent.type = "image/*"
            startActivityForResult(intent,fromeAlbum)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: 返回值 $requestCode")
        when(requestCode){
            takePhoto -> {
                if (resultCode == Activity.RESULT_OK){
                    //显示拍摄的图片
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))//将照片解析为Bitmap对象
                    binding.imageView.setImageBitmap(rotateIfRequired(bitmap))//显示
                    Log.d(TAG, "onActivityResult: 返回")
                }
            }
            fromeAlbum ->{
                if (resultCode == Activity.RESULT_OK && data != null){
                    data.data?.let {uri->
                        //显示选择的图片
                        val bitmap = getBitmapFromUri(uri)
                        binding.imageView.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    //判断照片是否需要旋转
    private fun rotateIfRequired(bitmap:Bitmap):Bitmap{
        val exif = ExifInterface(outputImage.path)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL)
        return when(orientation){
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap,90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap,180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap,270)
            else ->bitmap
        }
    }

    //旋转照片
    private fun rotateBitmap(bitmap: Bitmap, degree:Int):Bitmap{
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotateBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
        bitmap.recycle()//回收不需要的Bitmap对象
        return rotateBitmap
    }

    //从Uri内获取
    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri,"r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }
}