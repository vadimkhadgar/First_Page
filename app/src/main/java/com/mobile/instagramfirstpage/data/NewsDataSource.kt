package com.mobile.instagramfirstpage.data

import com.mobile.instagramfirstpage.model.News

object NewsDataSource {
    fun news(): ArrayList<News> {
        return arrayListOf<News>().apply {
            add(
                News(
                    id = 1,
                    typeContent = 1,
                    aspectRatio = "1:1",
                    pageName = "foxnews",
                    paragraph = "Something happened",
                    quantityOfComments = 1,
                    linkImage = "https://source.unsplash.com/random/900x900",
                    ifHasStory = true,
                    linkImageProfile = "https://source.unsplash.com/random/151x151",
                    turnOnEditText = true
                )
            )
            add(
                News(
                    id = 1,
                    typeContent = 1,
                    aspectRatio = "16:9",
                    pageName = "CNN",
                    paragraph = "Something happened",
                    quantityOfComments = 0,
                    linkImage = "https://source.unsplash.com/random/1920x1080",
                    turnOnEditText = true
                )
            )
            add(
                News(
                    id = 1,
                    typeContent = 2,
                    aspectRatio = "4:3",
                    pageName = "CNN",
                    paragraph = "Something happened",
                    quantityOfComments = 5,
                    linkImage = "https://source.unsplash.com/random/800x600",
                    turnOnEditText = true
                )
            )
            add(
                News(
                    id = 1,
                    typeContent = 2,
                    aspectRatio = "16:9",
                    pageName = "Sister",
                    paragraph = "Something happened",
                    quantityOfComments = 35,
                    linkImage = "https://source.unsplash.com/random/1921x1081"
                )
            )
            add(
                News(
                    id = 1,
                    typeContent = 1,
                    aspectRatio = "1:1",
                    pageName = "Sister",
                    paragraph = "Something happened",
                    quantityOfComments = 7,
                    linkImage = "https://source.unsplash.com/random/850x850"
                )
            )
            add(
                News(
                    id = 1,
                    typeContent = 2,
                    aspectRatio = "9:16",
                    pageName = "foxnews",
                    paragraph = "Something happened",
                    quantityOfComments = 216,
                    linkImage = "https://source.unsplash.com/random/1080x1920",
                    ifHasStory = true,
                    linkImageProfile = "https://source.unsplash.com/random/151x151"
                )
            )
            add(
                News(
                    id = 1,
                    typeContent = 1,
                    aspectRatio = "1:1",
                    pageName = "foxnews",
                    paragraph = "Something happened",
                    quantityOfComments = 1202,
                    linkImage = "https://source.unsplash.com/random/700x700",
                    ifHasStory = true,
                    linkImageProfile = "https://source.unsplash.com/random/151x151"
                )
            )
            add(
                News(
                    id = 1,
                    typeContent = 1,
                    aspectRatio = "1:1",
                    pageName = "Brother",
                    paragraph = "Something happened",
                    quantityOfComments = 0,
                    linkImage = "https://source.unsplash.com/random/800x800"
                )
            )
            add(
                News(
                    id = 1,
                    typeContent = 1,
                    aspectRatio = "3:5",
                    pageName = "foxnews",
                    paragraph = "Something happened",
                    quantityOfComments = 16,
                    linkImage = "https://source.unsplash.com/random/600x1000",
                    ifHasStory = true,
                    linkImageProfile = "https://source.unsplash.com/random/151x151"
                )
            )
        }
    }
}
