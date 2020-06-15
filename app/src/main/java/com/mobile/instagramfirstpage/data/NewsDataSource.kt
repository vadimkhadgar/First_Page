package com.mobile.instagramfirstpage.data

import com.mobile.instagramfirstpage.model.News

object NewsDataSource {
    fun news(): ArrayList<News> {
        return arrayListOf<News>().apply {
            add(News(1, "1:1","FoxChannel", "Something happened"))
            add(News(1, "16:9","FoxChannel", "Something happened"))
            add(News(2, "4:3","FoxChannel", "Something happened"))
            add(News(2, "16:9","FoxChannel", "Something happened"))
            add(News(1, "1:1","FoxChannel", "Something happened"))
            add(News(2, "9:16","FoxChannel", "Something happened"))
            add(News(1, "1:1","FoxChannel", "Something happened"))
            add(News(1, "1:1","FoxChannel", "Something happened"))
            add(News(1, "1:1","FoxChannel", "Something happened"))
        }
    }
}
