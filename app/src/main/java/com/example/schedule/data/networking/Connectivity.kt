package com.example.schedule.data.networking

import android.content.Context
import android.net.ConnectivityManager

class Connectivity(private val context: Context) {

    fun hasNetworkAccess(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivityManager.activeNetworkInfo
        return info != null && info.isConnected
    }
}