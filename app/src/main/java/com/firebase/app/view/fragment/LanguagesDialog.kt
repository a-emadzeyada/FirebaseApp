package com.firebase.app.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.firebase.app.common.SharedPreferencesUtils
import com.firebase.app.databinding.DialogLanguagesBinding
import com.firebase.app.view.activity.SplashActivity

class LanguagesDialog : DialogFragment() {
    private lateinit var mBinding: DialogLanguagesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DialogLanguagesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvEnglish.setOnClickListener {
            SharedPreferencesUtils.saveLanguage("en")
            restartApplication(activity as Context)
        }

        mBinding.tvArabic.setOnClickListener {
            SharedPreferencesUtils.saveLanguage("ar")
            restartApplication(activity as Context)
        }
    }

    private fun restartApplication(context: Context) {
        val i = Intent(context, SplashActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(i)
        activity?.finish()
    }

}