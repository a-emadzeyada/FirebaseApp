package com.firebase.app.common

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedPreferencesUtils {
    private const val DARK_THEME: String = "DARK_THEME"

    private const val FILE_NAME = "PrefFile"
    private const val NOT_INITIALIZED_ERROR = "Shared Preferences is not initialized"

    private var preferences: SharedPreferences? = null
    private var instance: SharedPreferencesUtils? = null


    fun getInstance(): SharedPreferencesUtils {
        if (instance == null) {
            throw IllegalStateException(NOT_INITIALIZED_ERROR)
        }
        return instance!!
    }

    fun initializeSharedPrefsService(context: Context) {
        instance = SharedPreferencesUtils
        instance!!.preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveString(key: String, value: String) {
        val sharedPreferencesEditor = preferences?.edit()
        sharedPreferencesEditor?.putString(key, value)
        sharedPreferencesEditor?.apply()
    }

    fun getStringValue(key: String, defaultValue: String?): String {
        return preferences!!.getString(key, defaultValue!!)!!
    }

    fun saveBoolean(key: String, value: Boolean) {
        try {
            val sharedPreferencesEditor = preferences?.edit()
            sharedPreferencesEditor?.putBoolean(key, value)
            sharedPreferencesEditor?.apply()
        } catch (e: Exception) {
            val s = e.message
        }
    }

    fun getBooleanValue(key: String, defaultValue: Boolean?): Boolean {
        return preferences!!.getBoolean(key, defaultValue!!)
    }

    fun saveLanguage(language: String) {
        saveString("language", language)
    }

    fun getLanguage(): String {
        return getStringValue("language", "en")
    }


    fun applyDarkTheme(isDarkTheme: Boolean) {
        getInstance().saveBoolean(DARK_THEME, isDarkTheme)
    }

    fun isDarkTheme(): Boolean {
        return getInstance().getBooleanValue(DARK_THEME, false)
    }
}