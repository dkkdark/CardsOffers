package com.kseniabl.cardstasks.ui.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("rating")
    var rating: Float,
    @SerializedName("isFreelancer")
    var isFreelancer: Boolean,
    @SerializedName("token")
    var token: String,

    @SerializedName("additionalInfo")
    var additionalInfo: AdditionalInfo,
    @SerializedName("profession")
    var profession: Profession
): Serializable