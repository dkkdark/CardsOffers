package com.kseniabl.cardtasks.ui.settings

import android.content.Context
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import co.lujun.androidtagview.TagContainerLayout
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardtasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.UsersCards
import com.kseniabl.cardstasks.ui.models.AdditionalInfo
import com.kseniabl.cardstasks.ui.models.Profession
import com.kseniabl.cardstasks.ui.settings.SettingsView
import com.kseniabl.cardtasks.ui.models.BaseProfileInfoModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import kotlin.collections.ArrayList

class SettingsPresenter<V: SettingsView, I: SettingsInteractorInterface> @Inject constructor(var context: Context, var interactor: I, var currentUserClass: CurrentUserClass): BasePresenter<V>(), SettingsPresenterInterface<V> {

    override fun logoutUser() {
        getView()?.editToken()
        UsersCards.clearCards()
        getView()?.openLoginActivity()
    }

    override fun setupUserBaseInfo(name: TextView, ratingStarView: RatingStarView, checkBox: CheckBox, email: TextView) {
        interactor.getUserName(currentUserClass.readSharedPref().id)
        .subscribe(object : Observer<BaseProfileInfoModel> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(data: BaseProfileInfoModel?) {
                if (data != null) {
                    name.text = data.username
                    ratingStarView.rating = data.rating
                    checkBox.isChecked = data.isFreelancer
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

    override fun setupUserProfession(tags: TagContainerLayout, spec: TextView, descr: TextView) {
        interactor.getUserProfession(currentUserClass.readSharedPref().id).subscribe(object : Observer<Profession> {
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

    override fun setupUserAdditionalInfo(descr: TextView, country: TextView, city: TextView, type: TextView) {
        interactor.getUserAdditionalInfo(currentUserClass.readSharedPref().id).subscribe(object : Observer<AdditionalInfo> {
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

    override fun changeIsFreelancerState(id: String, state: Boolean) {
        interactor.setFreelancerState(id, state)
    }

    override fun changeProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>) {
        interactor.setProfileProfessionField(id, spec, descr, tags)
    }

    override fun changeAdditionalInfo(id: String, descr: String, country: String, city: String, type: String) {
        interactor.setAdditionalInfoField(id, descr, country, city, type)
    }
}