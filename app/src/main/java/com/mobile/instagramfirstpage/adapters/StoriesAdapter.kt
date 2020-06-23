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
import kotlin.random.Random

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
            withContext(Dispatchers.Default) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val storyItem = getItem(position) as DataItem.StoryItem
                holder.bind(storyItem.story)
            }
            is TextViewHolder -> {
                val yourStoryItem = DataItem.Header
                holder.bind(yourStoryItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.StoryItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder(private val binding: HeaderStoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataItem.Header) {
            CoroutineScope(Dispatchers.Unconfined).launch {
                Glide.with(binding.ivPagePhotoHeader.context)
                    .load("https://source.unsplash.com/random/100x100")
                    .placeholder(R.drawable.ic_launcher_white)
                    .into(binding.ivPagePhotoHeader)
            }
        }

        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderStoryListBinding.inflate(layoutInflater, parent, false)
                return TextViewHolder(binding)
            }
        }
    }


    class ViewHolder private constructor(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Story) {
            CoroutineScope(Dispatchers.Unconfined).launch {
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
                // Из-за Random() возможна прогрузка новых картинок при скролле туда и обратно.
                val randomResolution = "15${Random.nextInt(0, 9)}x15${Random.nextInt(0, 9)}"
                val link = "https://source.unsplash.com/random/$randomResolution"
                Glide.with(binding.ivPagePhoto.context)
                    .load(link)
                    .placeholder(R.drawable.ic_launcher_white)
                    .into(binding.ivPagePhoto)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemStoryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
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