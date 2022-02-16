package com.firebase.app.application

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.firebase.app.common.SharedPreferencesUtils

class FireApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesUtils.initializeSharedPrefsService(applicationContext)
    }
    fun SmartAgendaApplication(context: Context) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}