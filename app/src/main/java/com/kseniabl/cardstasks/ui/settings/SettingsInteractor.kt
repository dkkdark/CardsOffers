package com.kseniabl.cardstasks.ui.settings

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.kseniabl.cardstasks.ui.base.*
import com.kseniabl.cardstasks.ui.models.AdditionalInfo
import com.kseniabl.cardstasks.ui.models.Profession
import com.kseniabl.cardtasks.ui.models.BaseProfileInfoModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableSubscriber
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

import retrofit2.Retrofit
import java.io.File
import java.io.InputStream
import java.nio.ByteBuffer


class SettingsInteractor @Inject constructor(val retrofit: Retrofit, var context: Context, var currentUserClass: CurrentUserClassInterface): SettingsInteractorInterface, UserCardInteractor() {

    override fun getUserProfession(id: String): Observable<Profession> {
        val observable = retrofit.create(RetrofitApiHolder::class.java).getUserProfession(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    override fun getUserName(id: String): Observable<BaseProfileInfoModel> {
        val observable = retrofit.create(RetrofitApiHolder::class.java).getUserName(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    override fun getUserAdditionalInfo(id: String): Observable<AdditionalInfo> {
        val observable = retrofit.create(RetrofitApiHolder::class.java).getUserAdditionalInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    override fun getProfileImage(id: String): Flowable<ImageModel> {
        val observable = retrofit.create(RetrofitApiHolder::class.java).getImg(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    override fun sendProfileInfoToServer() {
        val user = currentUserClass.readSharedPref()
        val json = Gson().toJson(user)

        retrofit.create(RetrofitApiHolder::class.java).updateProfileInfo(json)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : FlowableSubscriber<MessageModel> {
                override fun onSubscribe(s: Subscription) {
                    s.request(Long.MAX_VALUE)
                }

                override fun onNext(t: MessageModel) {
                    if (t.message == "success")
                        Log.e("qqq", "sendProfileInfoToServer OK")
                }

                override fun onError(t: Throwable) {
                    Log.e("qqq", "sendProfileInfoToServer error ${t.message}")
                }

                override fun onComplete() {
                }

            })
    }

    override fun setProfileName(id: String, name: String) {
        val user = currentUserClass.readSharedPref()
        user?.username = name
        user?.let { currentUserClass.saveCurrentUser(it) }

        /*retrofit.create(RetrofitApiHolder::class.java).setUserName(id, name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        Log.e("qqq", "setProfileName success")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("qqq", "onError ${e.message}")
                }

                override fun onComplete() {
                }
            })*/

    }

    override fun setFreelancerState(id: String, state: Boolean) {
        val user = currentUserClass.readSharedPref()
        user?.isFreelancer = state
        user?.let { currentUserClass.saveCurrentUser(it) }

        /*retrofit.create(RetrofitApiHolder::class.java).setIsFreelancerState(id, state)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: MessageModel?) {
                    if (data?.message == "success") {
                        Log.e("qqq", "setFreelancerState success")
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "set state onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })*/
    }

    override fun setProfileProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>) {
        val user = currentUserClass.readSharedPref()
        user?.profession?.description = descr
        user?.profession?.specialization = spec
        user?.profession?.tags = tags
        user?.let { currentUserClass.saveCurrentUser(it) }

        /*retrofit.create(RetrofitApiHolder::class.java).setUserProfession(id, descr, spec, tags)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        Log.e("qqq", "setProfileProfessionField success")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("qqq", "set prof onError ${e.message}")
                }

                override fun onComplete() {
                    Log.e("qqq", "set prof onComplete")
                }
            })*/
    }

    override fun setAdditionalInfoField(id: String, descr: String, country: String, city: String, type: String) {
        val user = currentUserClass.readSharedPref()
        user?.additionalInfo?.description = descr
        user?.additionalInfo?.country = country
        user?.additionalInfo?.city = city
        user?.additionalInfo?.typeOfWork = type
        user?.let { currentUserClass.saveCurrentUser(it) }

        /*retrofit.create(RetrofitApiHolder::class.java).setUserAdditionalInfo(id, descr, country, city, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        Log.e("qqq", "setAdditionalInfoField success")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("qqq", "set additional onError ${e.message}")
                }

                override fun onComplete() {
                }
            })*/
    }

    override fun clearToken(id: String, token: String) {
        retrofit.create(RetrofitApiHolder::class.java).clearToken(id, token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<MessageModel> {
                override fun onSubscribe(s: Subscription?) {
                }

                override fun onNext(m: MessageModel) {
                    if (m.message == "success")
                        Log.e("qqq", "token deleted successfully")
                }

                override fun onError(t: Throwable?) {
                    Log.e("qqq", "clearToken onError ${t?.message}")
                }

                override fun onComplete() {
                }

            })
    }

    override fun uploadImgToServer(id: String, requestBody: MultipartBody.Part): Flowable<ImageModel> {
        return retrofit.create(RetrofitApiHolder::class.java).uploadImg(id, requestBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}