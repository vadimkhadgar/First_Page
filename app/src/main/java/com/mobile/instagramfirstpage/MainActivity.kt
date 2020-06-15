package com.mobile.instagramfirstpage

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_photo_camera_24)

        val layoutManager = LinearLayoutManager(this)
        val adapter = StoriesAdapter(StoryDataSource.stories())
        recyclerViewStories.adapter = adapter


    }
}