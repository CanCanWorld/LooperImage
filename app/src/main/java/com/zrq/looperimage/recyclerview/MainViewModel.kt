package com.zrq.looperimage.recyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var mList = MutableLiveData<ArrayList<Int>>()
}