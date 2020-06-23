package com.mobile.instagramfirstpage.ui.notifications

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobile.instagramfirstpage.databinding.FragmentNotificationsBinding
import com.mobile.instagramfirstpage.ui.base.BaseFragment

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>(
    FragmentNotificationsBinding::inflate
) {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textNotifications.text = it
        })
    }
}