package com.zrq.looperimage.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zrq.looperimage.databinding.ItemItemBinding

class RecyclerAdapter(
    var context: Context,
    var list: ArrayList<Int>?
) : RecyclerView.Adapter<VH<ItemItemBinding>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH<ItemItemBinding> {
        val mBinding: ItemItemBinding =
            ItemItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return VH(mBinding)
    }

    override fun onBindViewHolder(holder: VH<ItemItemBinding>, position: Int) {
        if (list != null) {
            holder.binding.tvId.text = list?.get(position).toString()
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

}
