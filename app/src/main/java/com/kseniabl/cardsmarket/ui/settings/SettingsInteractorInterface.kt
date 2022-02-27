package com.kseniabl.cardsmarket.ui.settings

import com.kseniabl.cardsmarket.ui.base.BaseInteractor
import com.kseniabl.cardsmarket.ui.models.AdditionalInfo
import com.kseniabl.cardsmarket.ui.models.Profession
import com.kseniabl.cardsmarket.ui.models.BaseProfileInfoModel
import io.reactivex.rxjava3.core.Observable

interface SettingsInteractorInterface: BaseInteractor {
    fun setProfileName(id: String, name: String)
    fun setExecutorState(id: String, state: Boolean)
    fun setProfileProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>)
    fun setAdditionalInfoField(id: String, descr: String, country: String, city: String, type: String)

    fun getUserProfession(id: String): Observable<Profession>
    fun getUserName(id: String): Observable<BaseProfileInfoModel>
    fun getUserAdditionalInfo(id: String): Observable<AdditionalInfo>
}