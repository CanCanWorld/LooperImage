package com.zrq.looperimage

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class LooperViewPager : ViewPager {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN ->
                if (onLooperViewPagerTouch != null)
                    onLooperViewPagerTouch!!.onTouch(true)
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP ->
                if (onLooperViewPagerTouch != null)
                    onLooperViewPagerTouch!!.onTouch(false)
        }
        return super.onTouchEvent(ev)
    }

    private var onLooperViewPagerTouch: OnLooperViewPagerTouch? = null
    fun setOnLooperViewPagerTouch(onLooperViewPagerTouch: OnLooperViewPagerTouch?) {
        this.onLooperViewPagerTouch = onLooperViewPagerTouch
    }

    interface OnLooperViewPagerTouch {
        fun onTouch(touch: Boolean)
    }
}
