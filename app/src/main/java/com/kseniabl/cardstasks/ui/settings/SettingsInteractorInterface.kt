package com.kseniabl.cardstasks.ui.settings

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.kseniabl.cardstasks.ui.base.ImageModel
import com.kseniabl.cardtasks.ui.base.BaseInteractor
import com.kseniabl.cardstasks.ui.models.AdditionalInfo
import com.kseniabl.cardstasks.ui.models.Profession
import com.kseniabl.cardtasks.ui.models.BaseProfileInfoModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.File
import java.io.InputStream
import java.nio.ByteBuffer

interface SettingsInteractorInterface: BaseInteractor {
    fun setProfileName(id: String, name: String)
    fun setFreelancerState(id: String, state: Boolean)
    fun setProfileProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>)
    fun setAdditionalInfoField(id: String, descr: String, country: String, city: String, type: String)
    fun getProfileImage(id: String): Flowable<ImageModel>

    fun getUserProfession(id: String): Observable<Profession>
    fun getUserName(id: String): Observable<BaseProfileInfoModel>
    fun getUserAdditionalInfo(id: String): Observable<AdditionalInfo>

    fun clearToken(id: String, token: String)

    fun uploadImgToServer(id: String, requestBody: MultipartBody.Part): Flowable<MessageModel>
}