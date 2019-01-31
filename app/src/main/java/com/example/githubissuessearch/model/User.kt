package com.example.githubissuessearch.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    val id: Int,
    @SerializedName("avatar_url")
    var urlAvatar: String) : Serializable