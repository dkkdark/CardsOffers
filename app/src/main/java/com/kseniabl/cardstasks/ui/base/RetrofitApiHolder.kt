package com.kseniabl.cardstasks.ui.base

import com.google.gson.JsonObject
import com.kseniabl.cardstasks.ui.models.AdditionalInfo
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardstasks.ui.models.Profession
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.ui.models.*
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
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
    fun isCurrentUserExist(@Header("Authorization") token: String): Flowable<UserModel>


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

    @FormUrlEncoded
    @POST("update_profile_info")
    fun updateProfileInfo(@Field("profileInfo") json: String): Flowable<MessageModel>

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
    fun getAllCards(): Flowable<List<List<CardModel>>>

    @FormUrlEncoded
    @POST("delete_card")
    fun deleteCard(@Field("user_id") user_id: String,
                   @Field("card_id") card_id: String): Flowable<MessageModel>

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

    @FormUrlEncoded
    @POST("update_cards")
    fun updateCards(@Field("cards") json: String): Flowable<MessageModel>

     /*
        Freelancer methods
     */

    @GET("get_all_freelancers")
    fun getAllFreelancers(): Observable<List<FreelancerModel>>

    @GET("get_card_user/{user_id}")
    fun getCardUser(@Path("user_id") user_id: String): Observable<UserModel>

     /*
        Token
     */

    @FormUrlEncoded
    @POST("set_token")
    fun setToken(@Field("user_id") user_id: String,
                 @Field("token") token: String): Observable<MessageModel>

    @FormUrlEncoded
    @POST("replace_token")
    fun replaceToken(@Field("user_id") user_id: String,
                 @Field("old_token") old_token: String,
                 @Field("new_token") new_token: String): Flowable<MessageModel>

    @FormUrlEncoded
    @POST("clear_token")
    fun clearToken(@Field("user_id") user_id: String,
                 @Field("token") token: String): Flowable<MessageModel>

    @FormUrlEncoded
    @POST("send_message")
    fun sendMessage(@Field("sender_id") sender_id: String,
                    @Field("user_id") user_id: String,
                    @Field("title") title: String,
                    @Field("body") body: String,
                    @Field("card_id") card_id: String,
                    @Field("card_title") card_title: String,
                    @Field("card_cost") card_cost: String): Observable<MessageModel>

    @Multipart
    @POST("upload_img")
    fun uploadImg(
        @Part("user_id") user_id: String,
        @Part pic: MultipartBody.Part): Flowable<ImageModel>

    @GET("get_img/{user_id}")
    fun getImg (@Path("user_id") user_id: String): Flowable<ImageModel>
}