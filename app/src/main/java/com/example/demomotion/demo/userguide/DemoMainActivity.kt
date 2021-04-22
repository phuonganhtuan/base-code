package com.example.demomotion.demo.userguide

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.demomotion.MainActivity
import com.example.demomotion.MainViewModel
import com.example.demomotion.base.BaseActivity
import com.example.demomotion.databinding.ActivityMainBinding

class DemoMainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var sharedPreferences: SharedPreferences
    private var savedInstanceState: Bundle? = null

    override fun onActivityReady() {
        sharedPreferences = getSharedPreferences("com.example.userguide", MODE_PRIVATE)
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState
    }

    override fun onResume() {
        super.onResume()
        if (sharedPreferences.getBoolean("firstRun", true)) {
            val editor = sharedPreferences.edit()
            editor.putBoolean("firstRun", false).apply()
            openUserGuide()
            return
        }
        if (savedInstanceState == null) openMain()
    }

    private fun openUserGuide() {
        startActivity(Intent(this, UserGuideActivity::class.java))
        finish()
    }

    private fun openMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}