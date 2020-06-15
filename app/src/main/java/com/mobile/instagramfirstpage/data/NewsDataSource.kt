package com.mobile.instagramfirstpage.data

import com.mobile.instagramfirstpage.model.News

object NewsDataSource {
    fun news(): ArrayList<News> {
        return arrayListOf<News>().apply {
            add(News(1, "1:1","FoxChannel", "Something happened",1,"https://source.unsplash.com/random/800x800"))
            add(News(1, "16:9","CNN", "Something happened",0,"https://source.unsplash.com/random/1920x1080"))
            add(News(2, "4:3","CNN", "Something happened",5,"https://source.unsplash.com/random/800x600"))
            add(News(2, "16:9","Sister", "Something happened",35,"https://source.unsplash.com/random/1920x1080"))
            add(News(1, "1:1","Sister", "Something happened",7,"https://source.unsplash.com/random/800x800"))
            add(News(2, "9:16","FoxChannel", "Something happened",200,"https://source.unsplash.com/random/1080x1920"))
            add(News(1, "1:1","FoxChannel", "Something happened",1200,"https://source.unsplash.com/random/800x800"))
            add(News(1, "1:1","Brother", "Something happened",0,"https://source.unsplash.com/random/800x800"))
            add(News(1, "3:5","FoxChannel", "Something happened",16,"https://source.unsplash.com/random/600x1000"))
        }
    }
}
