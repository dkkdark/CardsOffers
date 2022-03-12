package com.kseniabl.cardtasks.ui.login

import com.google.gson.JsonObject
import com.kseniabl.cardtasks.ui.base.BaseInteractor
import io.reactivex.rxjava3.core.Observable

interface LoginInteractorInterface: BaseInteractor {
    fun registrationApiCall(name: String, email: String, password: String): Observable<JsonObject>
    fun loginApiCall(email: String, password: String): Observable<JsonObject>
    fun loadUserCards(id: String)
}