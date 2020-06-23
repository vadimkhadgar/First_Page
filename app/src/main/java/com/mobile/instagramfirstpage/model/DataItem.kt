package com.mobile.instagramfirstpage.model

sealed class DataItem {
    data class StoryItem(val story: Story) : DataItem() {
        override val id = story.id
    }

    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}