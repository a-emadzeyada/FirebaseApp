package com.firebase.app.common

import android.content.Context
import android.os.Build
import android.util.Log
import java.util.*

object LocaleHelper {
    const val TAG = "LocaleHelper"

    fun updateLocale(base: Context): Context {
        Log.e(TAG, "updateLocale: called");
        /*val language =
            if (SharedPreferencesUtils.getLanguage() == null) {
                "en"
            } else {
                SharedPreferencesUtils.getLanguage()
            }*/
        SharedPreferencesUtils.getLanguage().let {
            it
            return if (it.isNotEmpty()) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                    updateResources(base, it)
                } else {
                    updateResourcesLegacy(base, it)
                }
            } else {
                base
            }
        }
    }

    private fun updateResources(base: Context, language: String): Context {
        val loc = Locale(language)
        Locale.setDefault(loc)
        val configuration = base.resources.configuration
        configuration.setLocale(loc)
        return base.createConfigurationContext(configuration)
    }

    @Suppress("DEPRECATION")
    private fun updateResourcesLegacy(base: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = base.resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        base.resources.updateConfiguration(configuration, base.resources.displayMetrics)
//                    Log.e(TAG, "updateLocale: returning for below N")
        return base
    }
}