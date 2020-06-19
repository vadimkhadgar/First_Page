package com.mobile.instagramfirstpage.model

data class Story(
    val id: Long,
    val name: String,
    val imageProfileUrl: String,
    var wasRead: Boolean = false,
    var ifLiveStream: Boolean = false
)