package com.firebase.app.view.activity

import android.content.Intent
import android.os.Bundle
import com.firebase.app.common.SharedPreferencesUtils
import com.firebase.app.data.Model
import com.firebase.app.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var ref: DatabaseReference
    private val language = SharedPreferencesUtils.getLanguage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.imgSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        ref = FirebaseDatabase.getInstance().getReference("msg")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (language == "en")
                    mBinding.tvMsg.text = snapshot.getValue(Model::class.java)?.msg_en ?: ""
                else
                    mBinding.tvMsg.text = snapshot.getValue(Model::class.java)?.msg_ar ?: ""

            }

            override fun onCancelled(error: DatabaseError) {}

        })

    }
}