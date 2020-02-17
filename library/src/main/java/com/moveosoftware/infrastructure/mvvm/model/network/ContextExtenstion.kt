package com.moveosoftware.infrastructure.mvvm.model.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

@SuppressLint("MissingPermission")
        /**
 * Created by eliran on 26/02/2018.
         *
 */
fun Context.isNetworkStatusAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        it.activeNetworkInfo?.let {
            if (it.isConnected) return true
        }
    }
    return false
}