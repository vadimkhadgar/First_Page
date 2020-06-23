package com.mobile.instagramfirstpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.mobile.instagramfirstpage.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view1 = binding.root
        setContentView(view1)

        navController = Navigation.findNavController(
            this,
            R.id.mainContainer
        )
        binding.bottomNavigation.setupWithNavController(navController)

        // For image in bottom navigation view
        val bottomNavigationMenuView: BottomNavigationMenuView =
            binding.bottomNavigation.getChildAt(0) as BottomNavigationMenuView
        val view = bottomNavigationMenuView.getChildAt(4)
        val bottomNavigationItemView = view as BottomNavigationItemView
        val profileItem: View = LayoutInflater.from(view1.context)
            .inflate(R.layout.menu_profile_layout, bottomNavigationItemView, false)
        bottomNavigationItemView.addView(profileItem)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.mainContainer).navigateUp() || super.onSupportNavigateUp()

    }

    override fun onStart() {
        super.onStart()

        CoroutineScope(Dispatchers.Main).launch {
            val iv = findViewById<ImageView>(R.id.ivProfileBnv)
            Glide.with(this@MainActivity)
                .load("https://source.unsplash.com/random/100x100")
                .placeholder(R.drawable.ic_launcher_white)
                .into(iv)
        }
    }
}