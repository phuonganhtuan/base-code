package com.example.demomotion.demo.userguide

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.demomotion.MainActivity
import com.example.demomotion.MainViewModel
import com.example.demomotion.base.BaseActivity
import com.example.demomotion.databinding.ActivityUserGuideBinding
import com.example.demomotion.utils.gone
import com.example.demomotion.utils.show
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.system.exitProcess

class UserGuideActivity : BaseActivity<ActivityUserGuideBinding, MainViewModel>(){

    private val guiderAdapter = GuideAdapter()
    private val guiderList = mutableListOf<Guider>()

    override fun inflateViewBinding() = ActivityUserGuideBinding.inflate(layoutInflater)

    override fun onActivityReady()  {
        viewBinding.apply {

            pagerGuide.adapter = guiderAdapter
            for (i in 0..4) {
                guiderList.add(Guider())
            }
            guiderAdapter.guideList = guiderList
            TabLayoutMediator(tabGuide, pagerGuide) { tab, position -> }.attach()

            buttonStart.setOnClickListener { goToMainScreen() }
            buttonSkip.setOnClickListener { goToMainScreen() }
            buttonOut.setOnClickListener { exitProcess(0) }
            buttonCancel.setOnClickListener { exitProcess(0) }

            pagerGuide.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == guiderList.size - 1) {
                        buttonSkip.gone()
                        buttonCancel.gone()
                        buttonOut.show()
                        buttonStart.show()
                    } else {
                        buttonOut.gone()
                        buttonStart.gone()
                        buttonSkip.show()
                        buttonCancel.show()
                    }
                }
            })
        }
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    private fun goToMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
