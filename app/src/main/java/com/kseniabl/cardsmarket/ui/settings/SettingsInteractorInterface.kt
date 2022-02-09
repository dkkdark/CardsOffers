package com.kseniabl.cardsmarket.ui.settings

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.BaseInteractor

interface SettingsInteractorInterface: BaseInteractor {
    fun singOutOfAccount(): Task<Void>
    fun getUserName(): Task<DataSnapshot>?
    fun getUserEmail(): String?
    fun getProfession(): Task<DataSnapshot>?
    fun getAdditionalInfo(): Task<DataSnapshot>?
    fun setProfileName(name: String)
    fun setProfileProfessionField(spec: String, descr: String, tags: ArrayList<String>)
    fun setAdditionalInfoField(descr: String, country: String, city: String, type: String)
}