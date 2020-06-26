package com.mobile.instagramfirstpage.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.instagramfirstpage.MainActivity
import com.mobile.instagramfirstpage.R
import com.mobile.instagramfirstpage.adapters.NewsAdapter
import com.mobile.instagramfirstpage.databinding.FragmentHomeBinding
import com.mobile.instagramfirstpage.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

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

        binding.recyclerViewNews.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    //  Toast.makeText(requireContext(), "$dy", Toast.LENGTH_LONG).show()

                    val t =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val b =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    val c =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val v =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    Log.e(
                        "rtrt",
                        "onScrolled:\n full visible last $t\n full first visible $b\n partially visible last $c\n partially visible first $v"
                    )
                    Log.e("rtrt", recyclerView.isComputingLayout.toString())
                    if (!recyclerView.isComputingLayout) {
                        when (b) {
                            4 -> {
                                adapterNews.notifyItemChanged(b)
                                val view = recyclerViewNews.getChildAt(b)
                                view.findViewById<RelativeLayout>(R.id.relativeLayout).visibility =
                                    View.GONE
                            }
                        }
                    }
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })

    }
}