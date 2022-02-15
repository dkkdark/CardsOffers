package com.kseniabl.cardsmarket.ui.base

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("isExecutor")
    val isExecutor: Boolean
)