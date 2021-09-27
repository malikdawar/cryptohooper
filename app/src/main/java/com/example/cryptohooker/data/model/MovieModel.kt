package com.example.cryptohooker.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    @Expose val id: String,
    @Expose val author: String,
    @Expose val width: Long,
    @Expose val height: Long,
    @Expose val url: String,
    @Expose val download_url: String,
) : Parcelable