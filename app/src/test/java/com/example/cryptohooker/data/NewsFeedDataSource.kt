package com.example.cryptohooker.data

import com.example.cryptohooker.data.model.NewsFeedModel

object NewsFeedDataSource {

    fun getNewsFeedList(): ArrayList<NewsFeedModel> {
        val productList = ArrayList<NewsFeedModel>()
        productList.add(newsFeedModel)
        return productList
    }

    private val newsFeedModel: NewsFeedModel
        get() = NewsFeedModel(
            id = 1,
            createdAt = "Sept 2021, 12:23pm",
            userImageUrl = "http://dummyimage.com/500x500.png/ff4444/ffffff",
            postImage = "http://dummyimage.com/500x500.png/ff4444/ffffff",
            postMessage = "Lorem ipsum i sthe bets tsite to get thet dihskiyd ilhoi hoiuk",
            userFullName = "Lorem ipsum",
            likeCount = 23,
            commentCount = 233,

        )
}