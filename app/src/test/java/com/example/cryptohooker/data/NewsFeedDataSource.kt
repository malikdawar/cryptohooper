package com.example.cryptohooker.data

import com.example.cryptohooker.data.model.NewsFeedModel

object NewsFeedDataSource {

    fun getNewsFeedList(): ArrayList<NewsFeedModel> {
        val list = ArrayList<NewsFeedModel>()
        list.add(newsFeedModel)
        return list
    }

    private val newsFeedModel: NewsFeedModel
        get() = NewsFeedModel(
            id = 1,
            createdAt = "Sept 2021, 12:23pm",
            userImageUrl = "http://dummyimage.com/500x500.png/ff4444/ffffff",
            postImage = "http://dummyimage.com/500x500.png/ff4444/ffffff",
            postMessage = "Lorem ipsum is the best site to get thet dihskiyd ilhoi hoiuk",
            userFullName = "Lorem ipsum",
            likeCount = 233,
            commentCount = 233,

        )
}