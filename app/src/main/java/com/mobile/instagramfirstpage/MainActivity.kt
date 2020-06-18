package com.mobile.instagramfirstpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.mobile.instagramfirstpage.adapters.NewsAdapter
import com.mobile.instagramfirstpage.adapters.StoriesAdapter
import com.mobile.instagramfirstpage.data.NewsDataSource
import com.mobile.instagramfirstpage.data.StoryDataSource
import com.mobile.instagramfirstpage.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view1 = binding.root
        setContentView(view1)
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        val adapter =
            StoriesAdapter(StoryDataSource.stories())
        recyclerViewStories.adapter = adapter

        val adapter2 = NewsAdapter(NewsDataSource.news())
        recyclerViewNews.layoutManager = layoutManager
        recyclerViewNews.adapter = adapter2


        val bottomNavigationMenuView: BottomNavigationMenuView = binding.bottomNavigation.getChildAt(0) as BottomNavigationMenuView
        val view = bottomNavigationMenuView.getChildAt(4)
        val bottomNavigationItemView = view as BottomNavigationItemView
        val profileItem: View = LayoutInflater.from(view1.context).inflate(R.layout.menu_profile_layout, bottomNavigationItemView, false)
        bottomNavigationItemView.addView(profileItem)

    }
}