package com.mobile.instagramfirstpage.data

import com.mobile.instagramfirstpage.model.News

object NewsDataSource {
    fun news(): ArrayList<News> {
        return arrayListOf<News>().apply {
            add(News(1, "1:1","FoxChannel", "Something happened",1))
            add(News(1, "16:9","CNN", "Something happened",0))
            add(News(2, "4:3","CNN", "Something happened",5))
            add(News(2, "16:9","Sister", "Something happened",35))
            add(News(1, "1:1","Sister", "Something happened",7))
            add(News(2, "9:16","FoxChannel", "Something happened",200))
            add(News(1, "1:1","FoxChannel", "Something happened",1200))
            add(News(1, "1:1","Brother", "Something happened",0))
            add(News(1, "1:1","FoxChannel", "Something happened",16))
        }
    }
}
