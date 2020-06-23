package com.mobile.instagramfirstpage.data

import com.mobile.instagramfirstpage.model.Story

object StoryDataSource {
    fun stories(): ArrayList<Story>? {
        return arrayListOf<Story>().apply {
            add(
                Story(
                    id = 1,
                    name = "Uncle",
                    imageProfileUrl = "https://source.unsplash.com/random/150x150"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "foxnews",
                    imageProfileUrl = "https://source.unsplash.com/random/151x151",
                    ifLiveStream = true
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Nephew",
                    imageProfileUrl = "https://source.unsplash.com/random/152x152"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Cousin",
                    imageProfileUrl = "https://source.unsplash.com/random/153x153"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Father",
                    imageProfileUrl = "https://source.unsplash.com/random/154x154"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Mother",
                    imageProfileUrl = "https://source.unsplash.com/random/155x155"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Bob",
                    imageProfileUrl = "https://source.unsplash.com/random/156x156"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "john",
                    imageProfileUrl = "https://source.unsplash.com/random/157x157"
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Rosa",
                    imageProfileUrl = "https://source.unsplash.com/random/158x158",
                    wasRead = true
                )
            )
            add(
                Story(
                    id = 1,
                    name = "Jerry",
                    imageProfileUrl = "https://source.unsplash.com/random/159x159",
                    wasRead = true
                )
            )
        }
    }
}