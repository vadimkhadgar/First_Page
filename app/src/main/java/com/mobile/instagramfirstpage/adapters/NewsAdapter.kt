package com.mobile.instagramfirstpage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.instagramfirstpage.R
import com.mobile.instagramfirstpage.model.News
import kotlinx.android.synthetic.main.item_news_photo.view.*

class NewsAdapter(val peaceOfNews: ArrayList<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news_photo, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = peaceOfNews.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(peaceOfNews[position])
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvPageName = view.tvPageNameTop
        fun bind(news: News) {
            tvPageName.text = news.pageName
        }
    }
}