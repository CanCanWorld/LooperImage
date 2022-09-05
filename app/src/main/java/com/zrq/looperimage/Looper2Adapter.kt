package com.zrq.looperimage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zrq.looperimage.databinding.ItemViewPagerImgBinding
import kotlin.collections.ArrayList

class Looper2Adapter(
    var context: Context
) : RecyclerView.Adapter<Looper2Adapter.InnerHolder>() {

    var list: ArrayList<String>? = ArrayList()

    fun setData(data: ArrayList<String>) {
        if (list != null) {
            list!!.clear()
            list!!.addAll(data)
            notifyDataSetChanged()
        }
    }

    lateinit var imgBinding: ItemViewPagerImgBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        imgBinding = ItemViewPagerImgBinding.inflate(LayoutInflater.from(context), parent, false)
        Log.d(TAG, "onCreateViewHolder: ")
        return InnerHolder(imgBinding.root)
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: ")
        holder.setIsRecyclable(false)
        holder.setData(position)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ")
        return list!!.size + 2
    }

    inner class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {
            Glide.with(context)
                .load(list?.get(if (position >= list!!.size) position - list!!.size else position))
                .into(imgBinding.imageView)
        }
    }


    companion object {
        const val TAG = "Looper2Adapter"
    }

}
