package com.example.cryptohooker.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    @Expose val id: Int,
    @Expose val movieImage: String,
    @Expose val movieYear: Int,
    @Expose val movieGenre: String,
    @Expose val isRecommended: Boolean,
    @Expose val isPopular: Boolean,
    @Expose val movieTitle: String
) : Parcelable