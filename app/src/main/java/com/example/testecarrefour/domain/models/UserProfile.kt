package com.example.testecarrefour.domain.models

import com.google.gson.annotations.SerializedName

data class UserProfile(
    @SerializedName("avatar_url")
    var userAvatar: String,
    @SerializedName("html_url")
    var profileUrl: String,
    @SerializedName("name")
    var userName: String,
    var company: String?,
    var location: String?,
    @SerializedName("public_repos")
    var publicRepos: Int,
    var followers: Int,
    var following: Int
)