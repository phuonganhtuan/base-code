package com.example.demomotion

import android.os.Bundle
import com.example.demomotion.base.BaseActivity
import com.example.demomotion.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onActivityReady() {

    }

    override fun onActivityReady(savedInstanceState: Bundle?) {

    }
}
