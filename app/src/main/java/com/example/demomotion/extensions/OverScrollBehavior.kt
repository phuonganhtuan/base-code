package com.example.demomotion.extensions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

class OverScrollBehavior(context: Context, attributeSet: AttributeSet) :
    CoordinatorLayout.Behavior<View>() {

    private var overScrollY = 0

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        overScrollY = 0
        return true
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        overScrollY -= (dyUnconsumed / OVER_SCROLL_AREA)
        val group = target as ViewGroup
        val numberOfChildren = group.childCount
        for (i in 0 until numberOfChildren) {
            val view = group.getChildAt(i)
            view.translationY = overScrollY.toFloat()
        }
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        type: Int
    ) = moveToDefPosition(target)

    override fun onNestedPreFling(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (overScrollY == 0) {
            return false
        }
        moveToDefPosition(target)
        return true
    }

    private fun moveToDefPosition(target: View) {
        val group = target as ViewGroup
        val count = group.childCount
        for (i in 0 until count) {
            val view = group.getChildAt(i)
            ViewCompat.animate(view)
                .translationY(0f)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
    }

    companion object {
        private const val OVER_SCROLL_AREA = 4
    }
}
