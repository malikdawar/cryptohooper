package com.example.cryptohooker.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsFeedModel(
    @Expose val id: Int,
    @Expose val createdAt: String,
    @Expose val userImageUrl: String,
    @Expose val postImage: String?,
    @Expose val postMessage: String,
    @Expose val userFullName: String,
    @Expose val likeCount: Int,
    @Expose val commentCount: Int
) : Parcelable