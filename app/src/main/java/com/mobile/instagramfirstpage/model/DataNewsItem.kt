package com.mobile.instagramfirstpage.model

sealed class DataNewsItem {
    data class NewsItem(val news: News) : DataNewsItem() {
        override val id = news.id
    }

    object HeaderNews : DataNewsItem() {
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}