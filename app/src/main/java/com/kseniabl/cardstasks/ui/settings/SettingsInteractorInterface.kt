package com.kseniabl.cardstasks.ui.settings

import com.kseniabl.cardtasks.ui.base.BaseInteractor
import com.kseniabl.cardstasks.ui.models.AdditionalInfo
import com.kseniabl.cardstasks.ui.models.Profession
import com.kseniabl.cardtasks.ui.models.BaseProfileInfoModel
import io.reactivex.rxjava3.core.Observable

interface SettingsInteractorInterface: BaseInteractor {
    fun setProfileName(id: String, name: String)
    fun setFreelancerState(id: String, state: Boolean)
    fun setProfileProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>)
    fun setAdditionalInfoField(id: String, descr: String, country: String, city: String, type: String)

    fun getUserProfession(id: String): Observable<Profession>
    fun getUserName(id: String): Observable<BaseProfileInfoModel>
    fun getUserAdditionalInfo(id: String): Observable<AdditionalInfo>

    fun clearToken(id: String, token: String)
}