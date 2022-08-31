package com.zrq.looperimage

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.zrq.looperimage.LooperViewPager.OnLooperViewPagerTouch
import com.zrq.looperimage.bean.AdImage
import com.zrq.looperimage.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mPics = ArrayList<String>()
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var looperAdapter: LooperAdapter

    private var isTouch = false

    private val handler = Handler(Looper.myLooper()!!)

    private val mLooperTask: Runnable = object : Runnable {
        override fun run() {
            if (!isTouch) {
                var currentItem = mainBinding.viewPager.currentItem
                mainBinding.viewPager.setCurrentItem(++currentItem, true)
            }
            handler.postDelayed(this, 3000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initData()
        initEvent()
    }

    override fun onResume() {
        super.onResume()
        handler.post(mLooperTask)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(mLooperTask)
    }

    private fun initData() {

        looperAdapter = LooperAdapter(mPics)
        mainBinding.viewPager.apply {
            adapter = looperAdapter
            setCurrentItem(looperAdapter.getDataSize() * 100, false)

            setOnLooperViewPagerTouch(object : OnLooperViewPagerTouch {
                override fun onTouch(touch: Boolean) {
                    isTouch = touch
                }
            })
        }
    }

    private fun initEvent() {
        mainBinding.btnGet.setOnClickListener {
            val adImages = Gson().fromJson(JSON, AdImage::class.java)
            val mediaSetList = adImages.mediaSetList
            mediaSetList?.forEach {
                mPics.add(it.url)
                Log.d(TAG, "initEvent: ${it.filename}, ${it.url}, ${it.md}")
            }
            looperAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val JSON: String =
            "{\"play_order_image2\":\"xunlei1.jpg;xunlei2.jpg;xunlei3.jpg;xunlei4.jpg;xunlei5.jpg;xunlei6.jpg;xunlei7.jpg\",\"mediaSetList\":[{\"filename\":\"xunlei1.jpg\",\"url\":\"http://testsource.easytouch-manager.com/adimage/%E6%B5%8B%E8%AF%95%E5%85%AC%E5%8F%B8/xunlei1.jpg\",\"md5\":\"f6804390e7b0d2085db2e4085c761591\"},{\"filename\":\"xunlei2.jpg\",\"url\":\"http://testsource.easytouch-manager.com/adimage/%E6%B5%8B%E8%AF%95%E5%85%AC%E5%8F%B8/xunlei2.jpg\",\"md5\":\"6ed500b74201a639d78b3dac4af5ca62\"},{\"filename\":\"xunlei3.jpg\",\"url\":\"http://testsource.easytouch-manager.com/adimage/%E6%B5%8B%E8%AF%95%E5%85%AC%E5%8F%B8/xunlei3.jpg\",\"md5\":\"83d44efdb8271510fc94432f3fa0329c\"},{\"filename\":\"xunlei4.jpg\",\"url\":\"http://testsource.easytouch-manager.com/adimage/%E6%B5%8B%E8%AF%95%E5%85%AC%E5%8F%B8/xunlei4.jpg\",\"md5\":\"afb5ef4336bd94c5b43e604f6da760fd\"},{\"filename\":\"xunlei5.jpg\",\"url\":\"http://testsource.easytouch-manager.com/adimage/%E6%B5%8B%E8%AF%95%E5%85%AC%E5%8F%B8/xunlei5.jpg\",\"md5\":\"7d5d4f8aa335b9f2927d262c533c289d\"},{\"filename\":\"xunlei6.jpg\",\"url\":\"http://testsource.easytouch-manager.com/adimage/%E6%B5%8B%E8%AF%95%E5%85%AC%E5%8F%B8/xunlei6.jpg\",\"md5\":\"bf9550a2fbb09704d2e9a8e6caba9bc1\"},{\"filename\":\"xunlei7.jpg\",\"url\":\"http://testsource.easytouch-manager.com/adimage/%E6%B5%8B%E8%AF%95%E5%85%AC%E5%8F%B8/xunlei7.jpg\",\"md5\":\"08fdccedee8fb7e57fd9428d4c7a23e1\"}],\"type\":\"02\",\"play_order_image\":\"xunlei1.jpg;xunlei2.jpg;xunlei3.jpg;xunlei4.jpg;xunlei5.jpg;xunlei6.jpg;xunlei7.jpg\",\"play_order_image_time\":2,\"volume\":1,\"play_mode2\":\"2\",\"play_order_video\":\"\",\"play_order_image_time2\":3,\"avm\":\"86999988\",\"play_order_image_left\":\"\",\"play_order_image_pay\":\"xunlei1.jpg\",\"play_mode\":\"2\",\"time\":\"20220831142041\",\"play_order_video2\":null,\"play_order_image_right\":\"\"}"

        const val TAG = "MainActivity"
    }

}