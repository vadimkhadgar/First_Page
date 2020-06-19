package com.mobile.instagramfirstpage.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.instagramfirstpage.data.StoryDataSource
import com.mobile.instagramfirstpage.model.Story

class HomeViewModel : ViewModel() {

    private val _list = MutableLiveData<List<Story>>().apply {
        value = StoryDataSource.stories()
    }
    val list: LiveData<List<Story>> = _list
}