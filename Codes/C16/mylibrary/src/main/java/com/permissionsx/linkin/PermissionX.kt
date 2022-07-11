package com.permissionsx.linkin

import androidx.fragment.app.FragmentActivity

/**对外接口*/
object PermissionX {
    private const val TAG = "PermissionX"

    fun request(activity: FragmentActivity,vararg permissions: String,callback: PermissionCallback) {
        val fragmentmanager = activity.supportFragmentManager
        val existedFragment = fragmentmanager.findFragmentByTag(TAG)

        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentmanager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestnow(callback,*permissions)
    }
}