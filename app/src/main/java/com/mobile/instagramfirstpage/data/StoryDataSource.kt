package com.mobile.instagramfirstpage.data

import com.mobile.instagramfirstpage.model.Story

object StoryDataSource {
    fun stories(): ArrayList<Story> {
        return arrayListOf<Story>().apply {
            add(
                Story(
                    name = "Uncle",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    name = "Aunt",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    name = "Nephew",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    name = "Cousin",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    name = "Father",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    name = "Mother",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    name = "Bob",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    name = "john",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    name = "Rosa",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    name = "Jerry",
                    imageProfileUrl = "www.h.com"
                )
            )
        }
    }
}