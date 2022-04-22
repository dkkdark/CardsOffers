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
import kotlinx.android.synthetic.main.activity_profile.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import org.reactivestreams.Subscription
import java.io.File
import java.io.InputStream
import javax.inject.Inject
import kotlin.collections.ArrayList

class SettingsPresenter<V: SettingsView, I: SettingsInteractorInterface> @Inject constructor(var context: Context, var interactor: I,
                                                                                             var currentUserClass: CurrentUserClassInterface,
                                                                                             val messagesSaveAndLoad: MessagesSaveAndLoad, val chatListSaving: ChatListSavingInterface):
    BasePresenter<V>(), SettingsPresenterInterface<V> {

    override fun logoutUser() {
        val currentUser = currentUserClass.readSharedPref()
        if (currentUser != null) {
            currentUser.token = ""
            currentUserClass.saveCurrentUser(currentUser)
        }

        UsersCards.clearCards()
        messagesSaveAndLoad.dellAll()
        chatListSaving.saveChatList(arrayListOf())
        clearTokenFromServer()
        getView()?.openLoginActivity()
    }

    override fun setupUserBaseInfo(name: TextView, ratingStarView: RatingStarView, checkBox: CheckBox, email: TextView) {
        currentUserClass.readSharedPref()?.let {
            interactor.getUserName(it.id)
            .subscribe(object : Observer<BaseProfileInfoModel> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(data: BaseProfileInfoModel) {
                    name.text = data.username
                    ratingStarView.rating = data.rating
                    checkBox.isChecked = data.isFreelancer
                    email.text = data.email
                }

                override fun onError(e: Throwable) {
                    Log.e("qqq", "baseInfo show onError ${e.message}")
                }

                override fun onComplete() {
                }
            })
        }
    }

    override fun setupUserProfession(tags: TagContainerLayout, spec: TextView, descr: TextView) {
        currentUserClass.readSharedPref()?.let {
            interactor.getUserProfession(it.id).subscribe(object : Observer<Profession> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(data: Profession) {
                    tags.tags = data.tags
                    checkIsEmpty(spec, data.specialization)
                    checkIsEmpty(descr, data.description)
                }

                override fun onError(e: Throwable) {
                    Log.e("qqq", "profession show onError ${e.message}")
                }

                override fun onComplete() {
                }
            })
        }
    }

    override fun setupUserAdditionalInfo(descr: TextView, country: TextView, city: TextView, type: TextView) {
        currentUserClass.readSharedPref()?.let {
            interactor.getUserAdditionalInfo(it.id).subscribe(object : Observer<AdditionalInfo> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(data: AdditionalInfo) {
                    checkIsEmpty(descr, data.description)
                    checkIsEmpty(country, data.country)
                    checkIsEmpty(city, data.city)
                    checkIsEmpty(type, data.typeOfWork)
                }

                override fun onError(e: Throwable) {
                    Log.e("qqq", "additional show onError ${e.message}")
                }

                override fun onComplete() {
                }
            })
        }
    }

    override fun setupProfileImage(imageViewProfile: CircularImageView) {
        currentUserClass.readSharedPref()?.let {
            interactor.getProfileImage(it.id).subscribe(object : FlowableSubscriber<ImageModel> {
                override fun onSubscribe(s: Subscription) {
                    s.request(Long.MAX_VALUE)
                }

                override fun onNext(data: ImageModel) {
                    val img = data.img
                    val bytes = Base64.decode(img, Base64.DEFAULT)
                    Glide.with(context).load(bytes).into(imageViewProfile)
                }

                override fun onError(m: Throwable) {
                    Log.e("qqq", "setupProfileImage onError ${m.message}")
                }

                override fun onComplete() {
                }

            })
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

    override fun uploadImage(id: String, requestBody: MultipartBody.Part, imageViewProfile: CircularImageView, uri: Uri) {
        interactor.uploadImgToServer(id, requestBody)
            .subscribe(object : FlowableSubscriber<MessageModel> {
                override fun onSubscribe(s: Subscription) {
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        imageViewProfile.setImageURI(uri)
                    }
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