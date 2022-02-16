package com.firebase.app.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.firebase.app.common.LocaleHelper
import com.firebase.app.common.LocalizationUtil
import com.firebase.app.common.SharedPreferencesUtils
import com.firebase.app.common.SharedPreferencesUtils.isDarkTheme

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        val language = SharedPreferencesUtils.getLanguage()
        super.attachBaseContext(LocalizationUtil.applyLanguage(newBase!!, language))
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        LocaleHelper.updateLocale(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        applyDarkTheme()
    }

    fun applyDarkTheme() {
        if (isDarkTheme())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}