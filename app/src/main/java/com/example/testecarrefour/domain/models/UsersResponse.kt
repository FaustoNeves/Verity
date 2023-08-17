package com.example.testecarrefour.domain.models

import com.google.gson.annotations.SerializedName

class UsersResponse {

    @SerializedName("login")
    var userName: String? = ""

    @SerializedName("avatar_url")
    var userAvatar: String? = ""
}