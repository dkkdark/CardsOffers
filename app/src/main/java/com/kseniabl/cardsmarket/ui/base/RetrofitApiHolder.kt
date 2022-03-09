package com.kseniabl.cardsmarket.ui.base

import com.google.gson.JsonObject
import com.kseniabl.cardsmarket.ui.models.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApiHolder {

     /*
        Users methods
     */

    @FormUrlEncoded
    @POST("registration")
    fun createUser(@Field("user_id") user_id: String,
                   @Field("username") username: String,
                   @Field("email") email: String,
                   @Field("password") password: String) : Observable<JsonObject>

    @FormUrlEncoded
    @POST("login")
    fun checkLogin(@Field("email") email: String,
                   @Field("password") password: String): Observable<JsonObject>

    @GET("is_current_user_exist")
    fun isCurrentUserExist(@Header("Authorization") token: String): Observable<UserModel>


    @GET("get_profession/{user_id}")
    fun getUserProfession(@Path("user_id") user_id: String): Observable<Profession>

    @GET("get_name/{user_id}")
    fun getUserName(@Path("user_id") user_id: String): Observable<BaseProfileInfoModel>

    @GET("get_additional_info/{user_id}")
    fun getUserAdditionalInfo(@Path("user_id") user_id: String): Observable<AdditionalInfo>


    @FormUrlEncoded
    @POST("set_name")
    fun setUserName(@Field("id") user_id: String,
                    @Field("username") username: String): Observable<MessageModel>

    @FormUrlEncoded
    @POST("set_profession")
    fun setUserProfession(@Field("id") user_id: String,
                          @Field("description") description: String,
                          @Field("specialization") specialization: String,
                          @Field("tags") tags: ArrayList<String>): Observable<MessageModel>

    @FormUrlEncoded
    @POST("set_additional_info")
    fun setUserAdditionalInfo(@Field("id") user_id: String,
                              @Field("description") description: String,
                              @Field("country") country: String,
                              @Field("city") city: String,
                              @Field("typeOfWork") typeOfWork: String): Observable<MessageModel>

    @FormUrlEncoded
    @POST("set_is_freelancer_state")
    fun setIsFreelancerState(@Field("id") user_id: String,
                              @Field("isFreelancer") state: Boolean): Observable<MessageModel>


     /*
        Cards methods
     */

    @FormUrlEncoded
    @POST("add_new_card")
    fun addNewCard(@Field("id") user_id: String,
                   @Field("cardId") cardId: String,
                   @Field("title") title: String,
                   @Field("description") description: String,
                   @Field("date") date: String,
                   @Field("createTime") create_time: Long,
                   @Field("cost") cost: Int,
                   @Field("active") active: Boolean,
                   @Field("agreement") agreement: Boolean): Observable<MessageModel>

    @GET("get_users_cards/{user_id}")
    fun getUsersCards(@Path("user_id") user_id: String): Observable<List<CardModel>>


    @GET("get_all_cards")
    fun getAllCards(): Observable<List<List<CardModel>>>

    @FormUrlEncoded
    @POST("change_card")
    fun changeCard(@Field("id") user_id: String,
                   @Field("cardId") cardId: String,
                   @Field("title") title: String,
                   @Field("description") description: String,
                   @Field("date") date: String,
                   @Field("cost") cost: Int,
                   @Field("active") active: Boolean,
                   @Field("agreement") agreement: Boolean): Observable<MessageModel>

    /*
        Freelancer methods
     */

    @GET("get_all_freelancers")
    fun getAllFreelancers(): Observable<List<UserModel>>

    @GET("get_card_user/{user_id}")
    fun getCardUser(@Path("user_id") user_id: String): Observable<UserModel>
}