package com.zrq.looperimage

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import java.util.ArrayList

class LooperAdapter(var list: ArrayList<String>) : PagerAdapter() {
    override fun getCount(): Int {
        return if (list.size == 0)
            0 else Int.MAX_VALUE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        val realPos = if (list.size == 0) 0 else position % list.size
        if (list.size != 0) {
            Glide.with(container)
                .load(list[realPos])
                .into(imageView)
        }
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun getDataSize(): Int {
        return list.size
    }

}