package com.mobile.instagramfirstpage.model

// type: 1--photo, 2--video
data class News(
    val id: Long,
    val typeContent: Int,
    val aspectRatio: String,
    val pageName: String,
    val paragraph: String,
    val quantityOfComments: Int,
    val linkImage: String,
    var ifHasStory: Boolean = false,
    var linkImageProfile: String = "https://source.unsplash.com/random/50x50",
    var turnOnEditText: Boolean = false
)
