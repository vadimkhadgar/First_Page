package com.mobile.instagramfirstpage.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.instagramfirstpage.MainActivity
import com.mobile.instagramfirstpage.adapters.NewsAdapter
import com.mobile.instagramfirstpage.databinding.FragmentHomeBinding
import com.mobile.instagramfirstpage.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapterNews: NewsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.list.observe(viewLifecycleOwner, Observer { list ->
            adapterNews = NewsAdapter(list)
            val layoutManager = LinearLayoutManager(requireContext())
            homeViewModel.list2.observe(viewLifecycleOwner, Observer {
                binding.recyclerViewNews.layoutManager = layoutManager
                binding.recyclerViewNews.adapter = adapterNews
                adapterNews.addHeaderAndSubmitList(it)

            })
        })

    }
}