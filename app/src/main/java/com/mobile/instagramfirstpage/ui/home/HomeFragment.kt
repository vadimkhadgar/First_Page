package com.mobile.instagramfirstpage.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.instagramfirstpage.MainActivity
import com.mobile.instagramfirstpage.adapters.NewsAdapter
import com.mobile.instagramfirstpage.databinding.FragmentHomeBinding
import com.mobile.instagramfirstpage.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var homeViewModel: HomeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.list.observe(viewLifecycleOwner, Observer { list ->


            val adapterNews = NewsAdapter(list)
            val layoutManager = LinearLayoutManager(requireContext())
            homeViewModel.list2.observe(viewLifecycleOwner, Observer {
                binding.recyclerViewNews.layoutManager = layoutManager
                binding.recyclerViewNews.adapter = adapterNews
                adapterNews.addHeaderAndSubmitList(it)

            })
        })

        // TODO: 23.06.2020
        binding.recyclerViewNews.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    //  Toast.makeText(requireContext(), "$dy", Toast.LENGTH_LONG).show()
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })

    }
}