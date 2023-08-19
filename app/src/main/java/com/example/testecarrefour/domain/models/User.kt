package com.example.testecarrefour.domain.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    var userLogin: String,
    @SerializedName("avatar_url")
    var userAvatar: String
)