package com.kseniabl.cardstasks.ui.settings

import android.content.Context
import android.util.Log
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardstasks.ui.base.CurrentUserClassInterface
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.base.UserCardInteractor
import com.kseniabl.cardstasks.ui.models.AdditionalInfo
import com.kseniabl.cardstasks.ui.models.Profession
import com.kseniabl.cardtasks.ui.models.BaseProfileInfoModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

import retrofit2.Retrofit


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

    override fun setProfileName(id: String, name: String) {
        retrofit.create(RetrofitApiHolder::class.java).setUserName(id, name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: MessageModel?) {
                    if (data?.message == "success" && currentUserClass.readSharedPref() != null) {
                        currentUserClass.readSharedPref()!!.username = name
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })

    }

    override fun setFreelancerState(id: String, state: Boolean) {
        retrofit.create(RetrofitApiHolder::class.java).setIsFreelancerState(id, state)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: MessageModel?) {
                    if (data?.message == "success" && currentUserClass.readSharedPref() != null) {
                        currentUserClass.readSharedPref()!!.isFreelancer = state
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "set state onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })
    }

    override fun setProfileProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>) {
        retrofit.create(RetrofitApiHolder::class.java).setUserProfession(id, descr, spec, tags)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                    Log.e("qqq", "set prof onSubscribe")
                }

                override fun onNext(data: MessageModel?) {
                    if (data?.message == "success" && currentUserClass.readSharedPref() != null) {
                        currentUserClass.readSharedPref()!!.profession.description = descr
                        currentUserClass.readSharedPref()!!.profession.specialization = spec
                        currentUserClass.readSharedPref()!!.profession.tags = tags
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "set prof onError ${e?.message}")
                }

                override fun onComplete() {
                    Log.e("qqq", "set prof onComplete")
                }
            })
    }

    override fun setAdditionalInfoField(id: String, descr: String, country: String, city: String, type: String) {
        retrofit.create(RetrofitApiHolder::class.java).setUserAdditionalInfo(id, descr, country, city, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: MessageModel?) {
                    if (data?.message == "success" && currentUserClass.readSharedPref() != null) {
                        currentUserClass.readSharedPref()!!.additionalInfo.description = descr
                        currentUserClass.readSharedPref()!!.additionalInfo.country = country
                        currentUserClass.readSharedPref()!!.additionalInfo.city = city
                        currentUserClass.readSharedPref()!!.additionalInfo.typeOfWork = type
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "set additional onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })
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
}