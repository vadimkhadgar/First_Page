package com.mobile.instagramfirstpage.adapters

import android.annotation.SuppressLint
import android.text.Spannable
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.instagramfirstpage.R
import com.mobile.instagramfirstpage.databinding.HeaderNewsListBinding
import com.mobile.instagramfirstpage.databinding.ItemNewsContentBinding
import com.mobile.instagramfirstpage.model.DataNewsItem
import com.mobile.instagramfirstpage.model.News
import com.mobile.instagramfirstpage.model.Story
import com.mobile.instagramfirstpage.utils.CommentUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class NewsAdapter(private val list: List<Story>?) :
    ListAdapter<DataNewsItem, RecyclerView.ViewHolder>(NewsDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Unconfined)

    fun addHeaderAndSubmitList(list: List<News>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataNewsItem.HeaderNews)
                else -> listOf(DataNewsItem.HeaderNews) + list.map { DataNewsItem.NewsItem(it) }
            }
            withContext(Dispatchers.Default) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder -> {
                val newsItem = getItem(position) as DataNewsItem.NewsItem
                holder.bind(newsItem.news)
            }
            is HeaderViewHolder -> {
                val headerItem = DataNewsItem.HeaderNews
                holder.bind(headerItem, list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> NewsViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataNewsItem.HeaderNews -> ITEM_VIEW_TYPE_HEADER
            is DataNewsItem.NewsItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class HeaderViewHolder(private val binding: HeaderNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataNewsItem.HeaderNews, list: List<Story>?) {
            val adapter =
                StoriesAdapter()
            binding.recyclerViewStories.adapter = adapter
            adapter.addHeaderAndSubmitList(list)

        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderNewsListBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    class NewsViewHolder(private val binding: ItemNewsContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            CoroutineScope(Dispatchers.Default).launch {
                binding.tvPageNameTop.text = news.pageName
                binding.tvPageNameBottom.text = news.pageName
                // ImageContent Ratio
                val set = ConstraintSet()
                set.clone(binding.layout)
                set.setDimensionRatio(binding.ivContent.id, news.aspectRatio)
                set.applyTo(binding.layout)
                if (news.quantityOfComments > 0) {
                    binding.tvComments.text =
                        CommentUtil.intCommentToString(news.quantityOfComments)
                } else {
                    binding.tvComments.visibility = View.GONE
                }
                if (news.ifHasStory) {
                    binding.flWrapper.setBackgroundResource(R.drawable.background_rounded_with_stroke_small)
                } else {
                    binding.flWrapper.setBackgroundResource(R.drawable.background_rounded_small)
                }

                // Create spannable text like in Istagram
                val topic = "${news.pageName} ${news.paragraph}"
                val wordToSpan: Spannable =
                    SpannableString(topic)
                wordToSpan.setSpan(
                    android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                    0,
                    news.pageName.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.tvPageNameBottom.text = wordToSpan

                // Create spannable text like in Istagram
                val likes = binding.root.context.getString(R.string.liked_by_me)
                val wordToSpan1: Spannable =
                    SpannableString(likes)
                wordToSpan1.setSpan(
                    android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                    8,
                    likes.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.textView2.text = wordToSpan1
            }
            CoroutineScope(Dispatchers.Main).launch {
                Glide.with(binding.ivContent.context)
                    .load(news.linkImage)
                    .placeholder(R.drawable.ic_launcher_white)
                    .into(binding.ivContent)
            }
            CoroutineScope(Dispatchers.Main).launch {
                Glide.with(binding.ivPagePhoto.context)
                    .load(news.linkImage)
                    .placeholder(R.drawable.ic_launcher_white)
                    .into(binding.ivPagePhoto)
            }
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNewsContentBinding.inflate(layoutInflater, parent, false)
                return NewsViewHolder(binding)
            }
        }
    }
}

class NewsDiffCallback : DiffUtil.ItemCallback<DataNewsItem>() {
    override fun areItemsTheSame(oldItem: DataNewsItem, newItem: DataNewsItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataNewsItem, newItem: DataNewsItem): Boolean {
        return oldItem == newItem
    }
}