package com.kseniabl.cardtasks.ui.settings

import android.content.Context
import android.util.Log
import com.kseniabl.cardstasks.ui.base.CurrentUser
import com.kseniabl.cardtasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardtasks.ui.base.UserCardInteractor
import com.kseniabl.cardstasks.ui.models.AdditionalInfo
import com.kseniabl.cardstasks.ui.models.Profession
import com.kseniabl.cardtasks.ui.models.BaseProfileInfoModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

import retrofit2.Retrofit


class SettingsInteractor @Inject constructor(val retrofit: Retrofit, var context: Context): SettingsInteractorInterface, UserCardInteractor() {

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
                    if (data?.message == "success") {
                        CurrentUser.changeName(name)
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
                    if (data?.message == "success") {
                        CurrentUser.changeIsFreelancerState(state)
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
                    if (data?.message == "success") {
                        CurrentUser.changeProfession(descr, spec, tags)
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
                    if (data?.message == "success") {
                        Log.e("qqq", "suc")
                        CurrentUser.changeAdditional(descr, country, city, type)
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "set additional onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })
    }
}