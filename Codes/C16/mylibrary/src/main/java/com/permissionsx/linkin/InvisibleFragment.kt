package com.permissionsx.linkin

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import java.util.*

class InvisibleFragment : Fragment() {

    /**运行时权限回调通知*/
   // private var callback: ((Boolean, List<String>) -> Unit) ?= null
    private var callback: (PermissionCallback) ?= null

    fun requestnow(cb: (Boolean, List<String>) -> Unit,vararg permission: String) {
        callback = cb // 接收回调通知
        requestPermissions(permission,1) //请求权限
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1){
            val deniedList = ArrayList<String>() //存放被拒绝的权限
            for ((index,result) in grantResults.withIndex()){
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let {
                it(allGranted,deniedList)
            }
        }
    }
}

//给类型指定别名
typealias PermissionCallback = (Boolean, List<String>) -> Unit