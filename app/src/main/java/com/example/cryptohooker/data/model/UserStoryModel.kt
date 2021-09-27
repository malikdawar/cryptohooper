package com.example.cryptohooker.data.model

import com.google.gson.annotations.Expose

data class UserStoryModel(
    @Expose val image: Int,
    @Expose val userName: String
)