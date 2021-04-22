package com.example.demomotion.demo

import android.os.Bundle
import com.example.demomotion.MainViewModel
import com.example.demomotion.base.BaseActivity
import com.example.demomotion.databinding.LayoutCollapseToolBarBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class CollapsingToolbarActivity: BaseActivity<LayoutCollapseToolBarBinding, MainViewModel>() {

    override fun inflateViewBinding() = LayoutCollapseToolBarBinding.inflate(layoutInflater)

    override fun onActivityReady() {
        initViews()
        handleEvents()
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    private fun initViews() = with(viewBinding) {
        appBarMain.stateListAnimator = null
    }

    private fun handleEvents() = with(viewBinding) {
        appBarMain.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val scale = abs(verticalOffset.toFloat() / appBarMain.totalScrollRange.toFloat())
            textTitleToolbar.alpha = 1 - scale * 2
            textTaskTitle.alpha = scale
        })
    }
}
