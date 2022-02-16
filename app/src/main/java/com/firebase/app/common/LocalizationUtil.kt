package com.firebase.app.common


import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*


object LocalizationUtil {

    @SuppressWarnings("Deprecated in Android 17")
    fun applyLanguage(context: Context, language: String): Context {
        val config: Configuration = context.resources.configuration
        val sysLocale: Locale? = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            getSystemLocale(config)
        } else {
            getSystemLocaleLegacy(config)
        }
        if (language != "" && !sysLocale!!.language.equals(language)) {
            val locale = Locale(language)
            Locale.setDefault(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setSystemLocale(config, locale)
            } else {
                setSystemLocaleLegacy(config, locale)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(config)
        } else {
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }
        return context
    }

    private fun getSystemLocaleLegacy(config: Configuration): Locale? {
        return config.locale
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun getSystemLocale(config: Configuration): Locale? {
        return config.locales[0]
    }

    private fun setSystemLocaleLegacy(config: Configuration, locale: Locale?) {
        config.locale = locale
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun setSystemLocale(config: Configuration, locale: Locale?) {
        config.setLocale(locale)
    }

    fun setAppLocale(context: Context, localeCode: String) {
        val resources = context.resources
        val dm = resources.displayMetrics
        val config = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(Locale(localeCode.lowercase(Locale.getDefault())))
        } else {
            config.locale = Locale(localeCode.lowercase(Locale.getDefault()))
        }
        resources.updateConfiguration(config, dm)
    }
}