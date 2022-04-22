package com.kseniabl.cardstasks.ui.settings

import android.net.Uri
import android.widget.CheckBox
import android.widget.TextView
import co.lujun.androidtagview.TagContainerLayout
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardtasks.ui.base.PresenterInterface
import com.mikhaellopez.circularimageview.CircularImageView
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

interface SettingsPresenterInterface<V: SettingsView>: PresenterInterface<V> {
    fun logoutUser()
    fun setupUserBaseInfo(name: TextView, ratingStarView: RatingStarView, checkBox: CheckBox, email: TextView)
    fun setupUserProfession(tags: TagContainerLayout, spec: TextView, descr: TextView)
    fun setupUserAdditionalInfo(descr: TextView, country: TextView, city: TextView, type: TextView)
    fun setupProfileImage(imageViewProfile: CircularImageView)

    fun showUserName(name: TextView, username: String)
    fun showUserProfession(tagsLayout: TagContainerLayout, specText: TextView, descrText: TextView, spec: String, descr: String, tags: List<String>)
    fun showUserAdditionalInfo(descrText: TextView, countryText: TextView, cityText: TextView, typeText: TextView, descr: String, country: String, city: String, type: String)

    fun changeName(id: String, name: String)
    fun changeIsFreelancerState(id: String, state: Boolean)
    fun changeProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>)
    fun changeAdditionalInfo(id: String, descr: String, country: String, city: String, type: String)

    fun uploadImage(id: String, requestBody: MultipartBody.Part, imageViewProfile: CircularImageView, uri: Uri)
}