package com.kseniabl.cardstasks.ui.settings

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import co.lujun.androidtagview.TagContainerLayout
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardstasks.db.RepositoryInterface
import com.kseniabl.cardstasks.ui.base.*
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.models.AdditionalInfo
import com.kseniabl.cardstasks.ui.models.Profession
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.models.BaseProfileInfoModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import com.mikhaellopez.circularimageview.CircularImageView
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableSubscriber
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import org.reactivestreams.Subscription
import java.io.File
import java.io.InputStream
import javax.inject.Inject
import kotlin.collections.ArrayList

class SettingsPresenter<V: SettingsView, I: SettingsInteractorInterface> @Inject constructor(var context: Context, var interactor: I, val repository: RepositoryInterface,
                                                                                             var currentUserClass: CurrentUserClassInterface,
                                                                                             val messagesSaveAndLoad: MessagesSaveAndLoad, val chatListSaving: ChatListSavingInterface):
    BasePresenter<V>(), SettingsPresenterInterface<V> {

    override fun logoutUser() {
        val currentUser = currentUserClass.readSharedPref()
        if (currentUser != null) {
            currentUser.token = ""
            currentUserClass.saveCurrentUser(currentUser)
        }

        repository.deleteAddProdCardsList()
        messagesSaveAndLoad.dellAll()
        chatListSaving.saveChatList(arrayListOf())
        clearTokenFromServer()
        getView()?.openLoginActivity()
    }

    override fun setupUserBaseInfo(name: TextView, ratingStarView: RatingStarView, checkBox: CheckBox, email: TextView) {
        currentUserClass.readSharedPref()?.let {
            name.text = it.username
            ratingStarView.rating = it.rating
            checkBox.isChecked = it.isFreelancer
            email.text = it.email
        }
    }

    override fun setupUserProfession(tags: TagContainerLayout, spec: TextView, descr: TextView) {
        currentUserClass.readSharedPref()?.let {
            tags.tags = it.profession.tags
            checkIsEmpty(spec, it.profession.specialization)
            checkIsEmpty(descr, it.profession.description)

        }
    }

    override fun setupUserAdditionalInfo(descr: TextView, country: TextView, city: TextView, type: TextView) {
        currentUserClass.readSharedPref()?.let {
            checkIsEmpty(descr, it.additionalInfo.description)
            checkIsEmpty(country, it.additionalInfo.country)
            checkIsEmpty(city, it.additionalInfo.city)
            checkIsEmpty(type, it.additionalInfo.typeOfWork)
        }

    }

    override fun setupProfileImage(imageViewProfile: CircularImageView) {
        currentUserClass.readSharedPref()?.let {
            val img = it.img
            if (!img.isNullOrEmpty()) {
                val bytes = Base64.decode(img, Base64.DEFAULT)
                Glide.with(context).load(bytes).placeholder(R.drawable.user).into(imageViewProfile)
            }
            else {
                interactor.getProfileImage(it.id).subscribe(object : FlowableSubscriber<ImageModel> {
                    override fun onSubscribe(s: Subscription) {
                        s.request(Long.MAX_VALUE)
                    }

                    override fun onNext(data: ImageModel) {
                        val bytes = Base64.decode(data.img, Base64.DEFAULT)
                        Glide.with(context).load(bytes).placeholder(R.drawable.user).into(imageViewProfile)
                    }

                    override fun onError(t: Throwable) {
                        Log.e("qqq", "setupProfileImage onError ${t.message}")
                    }

                    override fun onComplete() {
                    }

                })
            }
        }
    }

    override fun showUserName(name: TextView, username: String) {
        name.text = username
    }

    override fun showUserProfession(tagsLayout: TagContainerLayout, specText: TextView, descrText: TextView, spec: String, descr: String, tags: List<String>) {
        checkIsEmpty(specText, spec)
        checkIsEmpty(descrText, descr)
        tagsLayout.tags = tags
    }

    override fun showUserAdditionalInfo(descrText: TextView, countryText: TextView, cityText: TextView, typeText: TextView, descr: String, country: String, city: String, type: String) {
        checkIsEmpty(descrText, descr)
        checkIsEmpty(countryText, country)
        checkIsEmpty(cityText, city)
        checkIsEmpty(typeText, type)
    }

    private fun checkIsEmpty(textView: TextView, value: String) {
        if (value.isNotEmpty())
            textView.text = value
        else
            textView.text = "—"
    }

    override fun changeName(id: String, name: String) {
        interactor.setProfileName(id, name)
    }

    override fun changeIsFreelancerState(id: String, state: Boolean) {
        interactor.setFreelancerState(id, state)
    }

    override fun changeProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>) {
        interactor.setProfileProfessionField(id, spec, descr, tags)
    }

    override fun changeAdditionalInfo(id: String, descr: String, country: String, city: String, type: String) {
        interactor.setAdditionalInfoField(id, descr, country, city, type)
    }

    override fun sendProfileInfoToServer() {
        interactor.sendProfileInfoToServer()
    }

    private fun clearTokenFromServer() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("qqq", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            currentUserClass.readSharedPref()?.let { interactor.clearToken(it.id, token) }
        })
    }

    override fun uploadImage(id: String, requestBody: MultipartBody.Part, imageViewProfile: CircularImageView, uri: Uri, byteArray: ByteArray) {
        interactor.uploadImgToServer(id, requestBody)
            .subscribe(object : FlowableSubscriber<ImageModel> {
                override fun onSubscribe(s: Subscription) {
                    s.request(Long.MAX_VALUE)
                }

                override fun onNext(data: ImageModel) {
                    val bytes = Base64.decode(data.img, Base64.DEFAULT)
                    Glide.with(context).load(bytes).placeholder(R.drawable.user).into(imageViewProfile)
                    val user = currentUserClass.readSharedPref()
                    user?.img = data.img
                    user?.let { currentUserClass.saveCurrentUser(it) }
                }

                override fun onError(t: Throwable) {
                    Log.e("qqq", "uploadImage onError ${t.message}")
                    Toast.makeText(context, "Sorry, image wasn't upload", Toast.LENGTH_SHORT).show()
                }

                override fun onComplete() {
                }

            })
    }
}