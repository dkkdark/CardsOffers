package com.kseniabl.cardsmarket.ui.settings

import android.content.Context
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import co.lujun.androidtagview.TagContainerLayout
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.CurrentUser
import com.kseniabl.cardsmarket.ui.base.UsersCards
import com.kseniabl.cardsmarket.ui.models.AdditionalInfo
import com.kseniabl.cardsmarket.ui.models.Profession
import com.kseniabl.cardsmarket.ui.models.BaseProfileInfoModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import kotlin.collections.ArrayList

class SettingsPresenter<V: SettingsView, I: SettingsInteractorInterface> @Inject constructor(var context: Context, var interactor: I): BasePresenter<V>(), SettingsPresenterInterface<V> {

    override fun logoutUser() {
        getView()?.editToken()
        UsersCards.clearCards()
        getView()?.openLoginActivity()
    }

    override fun setupUserBaseInfo(name: TextView, ratingStarView: RatingStarView, checkBox: CheckBox, email: TextView) {
        CurrentUser.getUser()?.id?.let {
            interactor.getUserName(it)
            .subscribe(object : Observer<BaseProfileInfoModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: BaseProfileInfoModel?) {
                    if (data != null) {
                        name.text = data.username
                        ratingStarView.rating = data.rating
                        checkBox.isChecked = data.isExecutor
                        email.text = data.email
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "baseInfo show onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })
        }
    }

    override fun setupUserProfession(tags: TagContainerLayout, spec: TextView, descr: TextView) {
        CurrentUser.getUser()?.id?.let {
            interactor.getUserProfession(it).subscribe(object : Observer<Profession> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: Profession?) {
                    if (data != null) {
                        tags.tags = data.tags
                        if (data.specialization != "")
                            spec.text = data.specialization
                        else
                            spec.text = "—"
                        if (data.description != "")
                            descr.text = data.description
                        else
                            descr.text = "—"
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "profession show onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })
        }
    }

    override fun setupUserAdditionalInfo(descr: TextView, country: TextView, city: TextView, type: TextView) {
        CurrentUser.getUser()?.id?.let {
            interactor.getUserAdditionalInfo(it).subscribe(object : Observer<AdditionalInfo> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: AdditionalInfo?) {
                    if (data != null) {
                        if (data.description != "")
                            descr.text = data.description
                        else
                            descr.text = "—"
                        if (data.country != "")
                            country.text = data.country
                        else
                            country.text = "—"
                        if (data.city != "")
                            city.text = data.city
                        else
                            city.text = "—"
                        if (data.typeOfWork != "")
                            type.text = data.typeOfWork
                        else
                            type.text = "—"
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "additional show onError ${e?.message}")
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
        specText.text = spec
        descrText.text = descr
        tagsLayout.tags = tags
    }

    override fun showUserAdditionalInfo(descrText: TextView, countryText: TextView, cityText: TextView, typeText: TextView, descr: String, country: String, city: String, type: String) {
        descrText.text = descr
        countryText.text = country
        cityText.text = city
        typeText.text = type
    }

    override fun changeName(id: String, name: String) {
        interactor.setProfileName(id, name)
    }

    override fun changeIsExecutorState(id: String, state: Boolean) {
        interactor.setExecutorState(id, state)
    }

    override fun changeProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>) {
        interactor.setProfileProfessionField(id, spec, descr, tags)
    }

    override fun changeAdditionalInfo(id: String, descr: String, country: String, city: String, type: String) {
        interactor.setAdditionalInfoField(id, descr, country, city, type)
    }
}