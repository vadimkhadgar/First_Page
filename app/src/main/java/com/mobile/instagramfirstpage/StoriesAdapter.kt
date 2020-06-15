package com.mobile.instagramfirstpage

import android.view.ViewGroup
import com.mobile.instagramfirstpage.databinding.ItemStoryBinding

class StoriesAdapter(
    private val values: List<Story>
) : BaseAdapterCustom<StoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBinding(ItemStoryBinding::inflate))
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    class ViewHolder(private val binding: ItemStoryBinding) :
        BaseViewHolder(binding) {

        fun bind(item: Story) {
            binding.tvName.text = item.name
        }
    }
}