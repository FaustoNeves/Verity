package com.example.testecarrefour.domain.models

import com.google.gson.annotations.SerializedName

data class GitRepo(
    var name: String,
    @SerializedName("html_url")
    var htmlUrl: String,
    var language: String,
    var description: String
)