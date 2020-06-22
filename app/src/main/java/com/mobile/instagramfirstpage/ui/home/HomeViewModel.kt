package com.mobile.instagramfirstpage.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.instagramfirstpage.data.NewsDataSource
import com.mobile.instagramfirstpage.data.StoryDataSource
import com.mobile.instagramfirstpage.model.News
import com.mobile.instagramfirstpage.model.Story
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _list = MutableLiveData<List<Story>>().apply {
        viewModelScope.launch {
            value = StoryDataSource.stories()
        }
    }
    val list: LiveData<List<Story>> = _list

    private val _list_ = MutableLiveData<ArrayList<News>>().apply {
        viewModelScope.launch {
            value = NewsDataSource.news()
        }
    }
    val list2: LiveData<ArrayList<News>> = _list_
}