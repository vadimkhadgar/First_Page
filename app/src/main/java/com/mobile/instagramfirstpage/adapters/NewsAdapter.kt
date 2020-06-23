package com.mobile.instagramfirstpage.adapters

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.instagramfirstpage.R
import com.mobile.instagramfirstpage.databinding.ItemNewsContentBinding
import com.mobile.instagramfirstpage.model.News
import com.mobile.instagramfirstpage.utils.CommentUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsAdapter(private val peaceOfNews: ArrayList<News>) :
    BaseAdapterCustom<NewsAdapter.NewsViewHolder>() {

    private val adapterScope = CoroutineScope(Dispatchers.Unconfined)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            parent.inflateBinding(ItemNewsContentBinding::inflate)
        )
    }

    override fun getItemCount() = peaceOfNews.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(peaceOfNews[position])
    }

    inner class NewsViewHolder(private val binding: ItemNewsContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            adapterScope.launch {
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
            }
            adapterScope.launch {
                Glide.with(binding.ivContent.context)
                    .load(news.linkImage)
                    .placeholder(R.drawable.ic_launcher_white)
                    .into(binding.ivContent)
            }
            adapterScope.launch {
                Glide.with(binding.ivPagePhoto.context)
                    .load(news.linkImage)
                    .placeholder(R.drawable.ic_launcher_white)
                    .into(binding.ivPagePhoto)
            }

        }
    }
}