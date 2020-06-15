package com.mobile.instagramfirstpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.instagramfirstpage.adapters.NewsAdapter
import com.mobile.instagramfirstpage.adapters.StoriesAdapter
import com.mobile.instagramfirstpage.data.NewsDataSource
import com.mobile.instagramfirstpage.data.StoryDataSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        val adapter =
            StoriesAdapter(StoryDataSource.stories())
        recyclerViewStories.adapter = adapter

        val adapter2 = NewsAdapter(NewsDataSource.news())
        recyclerViewNews.layoutManager = layoutManager
        recyclerViewNews.adapter = adapter2
    }
}