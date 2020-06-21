package com.mobile.instagramfirstpage.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.instagramfirstpage.MainActivity
import com.mobile.instagramfirstpage.R
import com.mobile.instagramfirstpage.adapters.NewsAdapter
import com.mobile.instagramfirstpage.adapters.StoriesAdapter
import com.mobile.instagramfirstpage.data.NewsDataSource
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(requireContext())
        val adapter =
            StoriesAdapter()
        recyclerViewStories.adapter = adapter
        homeViewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.addHeaderAndSubmitList(it)
        })

        val adapter2 = NewsAdapter(NewsDataSource.news())
        recyclerViewNews.layoutManager = layoutManager
        recyclerViewNews.adapter = adapter2
    }
}