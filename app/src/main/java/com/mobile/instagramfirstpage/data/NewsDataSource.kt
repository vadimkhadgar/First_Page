package com.mobile.instagramfirstpage.data

import com.mobile.instagramfirstpage.model.News

object NewsDataSource {
    fun news(): ArrayList<News> {
        return arrayListOf<News>().apply {
            add(News(1, "FoxChannel", "Something happened"))
            add(News(1, "FoxChannel", "Something happened"))
            add(News(2, "FoxChannel", "Something happened"))
            add(News(2, "FoxChannel", "Something happened"))
            add(News(1, "FoxChannel", "Something happened"))
            add(News(2, "FoxChannel", "Something happened"))
            add(News(1, "FoxChannel", "Something happened"))
            add(News(1, "FoxChannel", "Something happened"))
            add(News(1, "FoxChannel", "Something happened"))
        }
    }
}
