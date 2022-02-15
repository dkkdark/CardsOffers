package com.kseniabl.cardsmarket.ui.base

import com.firebase.ui.auth.data.model.User
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApiHolder {

    @FormUrlEncoded
    @POST("registration")
    fun createUser(@Field("username") username: String,
                   @Field("email") email: String,
                   @Field("password") password: String) : Call<JsonObject>

    @FormUrlEncoded
    @POST("login")
    fun checkLogin(@Field("email") email: String,
                   @Field("password") password: String): Call<JsonObject>

    /*@Headers("Content-Type: application/json")
    @POST("registration")
    fun createUser(@Body requestBody: UserModel): Call<UserModel>*/
}