package com.mobile.instagramfirstpage.data

import com.mobile.instagramfirstpage.model.Story

object StoryDataSource {
    fun stories(): ArrayList<Story>? {
        return arrayListOf<Story>().apply {
            add(
                Story(
                    id = 1,
                    name = "Uncle",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Aunt",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Nephew",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Cousin",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Father",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Mother",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Bob",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "john",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Rosa",
                    imageProfileUrl = "www.h.com"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Jerry",
                    imageProfileUrl = "www.h.com"
                )
            )
        }
    }
}