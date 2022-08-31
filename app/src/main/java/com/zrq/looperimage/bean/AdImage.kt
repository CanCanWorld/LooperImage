package com.zrq.looperimage.bean

data class AdImage(val playOrderImage: String = "",
                   val mediaSetList: List<MediaSetListItem>?,
                   val type: String = "",
                   val playOrderImageTime: Int = 0,
                   val volume: Int = 0,
                   val playMode: String = "",
                   val playOrderVideo: String = "",
                   val avm: String = "",
                   val playOrderImageLeft: String = "",
                   val playOrderImagePay: String = "",
                   val time: String = "",
                   val playOrderImageRight: String = "")

data class MediaSetListItem(val filename: String = "",
                            val url: String = "",
                            val md: String = "")