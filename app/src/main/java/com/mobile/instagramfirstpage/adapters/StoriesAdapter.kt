package com.mobile.instagramfirstpage.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.instagramfirstpage.R
import com.mobile.instagramfirstpage.databinding.HeaderStoryListBinding
import com.mobile.instagramfirstpage.databinding.ItemStoryBinding
import com.mobile.instagramfirstpage.model.DataItem
import com.mobile.instagramfirstpage.model.Story
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class StoriesAdapter :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(StoriesDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Story>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.StoryItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StoriesViewHolder -> {
                val storyItem = getItem(position) as DataItem.StoryItem
                holder.bind(storyItem.story)
            }
            is HeaderViewHolder -> {
                val yourStoryItem = DataItem.Header
                holder.bind(yourStoryItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> StoriesViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.StoryItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class HeaderViewHolder(private val binding: HeaderStoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataItem.Header) {
            CoroutineScope(Dispatchers.Main).launch {
                Glide.with(binding.ivPagePhotoHeader.context)
                    .load("https://source.unsplash.com/random/100x100")
                    .placeholder(R.drawable.ic_launcher_white)
                    .into(binding.ivPagePhotoHeader)
            }
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderStoryListBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    class StoriesViewHolder private constructor(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Story) {
            CoroutineScope(Dispatchers.Main).launch {
                binding.tvName.text = item.name
                if (item.wasRead) {
                    binding.frameLayout.setBackgroundResource(R.drawable.background_rounded)
                } else {
                    binding.frameLayout.setBackgroundResource(R.drawable.background_rounded_with_stroke)
                }
                if (item.ifLiveStream) {
                    binding.tvLive.visibility = View.VISIBLE
                } else {
                    binding.tvLive.visibility = View.GONE
                }
                Glide.with(binding.ivPagePhoto.context)
                    .load(item.imageProfileUrl)
                    .placeholder(R.drawable.ic_launcher_white)
                    .into(binding.ivPagePhoto)
            }
        }

        companion object {
            fun from(parent: ViewGroup): StoriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemStoryBinding.inflate(layoutInflater, parent, false)
                return StoriesViewHolder(binding)
            }
        }
    }
}

class StoriesDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}