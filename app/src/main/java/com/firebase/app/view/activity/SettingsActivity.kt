package com.firebase.app.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.firebase.app.R
import com.firebase.app.common.SharedPreferencesUtils
import com.firebase.app.databinding.ActivitySettingsBinding
import com.firebase.app.view.fragment.LanguagesDialog

class SettingsActivity : BaseActivity() {
    private lateinit var mBinding: ActivitySettingsBinding
    private val language = SharedPreferencesUtils.getLanguage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        init()
    }

    private fun init() {

        val isDark = SharedPreferencesUtils.isDarkTheme()
        mBinding.swDark.isChecked = isDark
        if (language == "en") {
            mBinding.tvLanguage.setText(R.string.english)
        } else {
            mBinding.tvLanguage.setText(R.string.arabic)
        }

        mBinding.vLanguage.setOnClickListener {
            val dialogFragment = LanguagesDialog()
            dialogFragment.show(supportFragmentManager, "dialog")
        }
        mBinding.swDark.setOnCheckedChangeListener { _, isChecked ->
            SharedPreferencesUtils.applyDarkTheme(isChecked)
            applyDarkTheme()
            restartApplication(this)
        }
    }
    private fun restartApplication(context: Context) {
        val i = Intent(context, SplashActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(i)
        finish()
    }
}