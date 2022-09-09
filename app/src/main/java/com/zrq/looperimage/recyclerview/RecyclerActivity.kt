package com.zrq.looperimage.recyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.zrq.looperimage.R
import com.zrq.looperimage.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    lateinit var recyclerBinding: ActivityRecyclerBinding
    var list: ArrayList<Int> = ArrayList()
    lateinit var mainViewModel: MainViewModel
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerBinding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(recyclerBinding.root)
        initData()
        initEvent()
    }

    private fun initData() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val manager = GridLayoutManager(this, 5)
        recyclerBinding.recyclerView.layoutManager = manager
        adapter = RecyclerAdapter(this, list)
        recyclerBinding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initEvent() {
        mainViewModel.mList.observe(this, object : Observer<ArrayList<Int>> {
            override fun onChanged(t: ArrayList<Int>?) {
                if (t != null) {
                    list.clear()
                    list.addAll(t)
                    adapter.notifyDataSetChanged()
                }
            }

        })

        recyclerBinding.btnGet.setOnClickListener {
            val data = ArrayList<Int>()
            for (i in 1..1000) {
                data.add(i)
            }
            mainViewModel.mList.postValue(data)
        }

        recyclerBinding.btnGet2.setOnClickListener {
            list.clear()
            for (i in 1..100) {
                list.add(i)
            }
            adapter.notifyDataSetChanged()
        }
    }
}