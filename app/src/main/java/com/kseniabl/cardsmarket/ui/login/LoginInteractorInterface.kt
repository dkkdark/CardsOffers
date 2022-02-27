package com.kseniabl.cardsmarket.ui.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DataSnapshot
import com.google.gson.JsonObject
import com.kseniabl.cardsmarket.ui.base.BaseInteractor
import com.kseniabl.cardsmarket.ui.models.UserModel
import io.reactivex.rxjava3.core.Observable

interface LoginInteractorInterface: BaseInteractor {
    fun registrationApiCall(name: String, email: String, password: String): Observable<JsonObject>
    fun loginApiCall(email: String, password: String): Observable<JsonObject>
    fun loadUserCards(id: String)
}