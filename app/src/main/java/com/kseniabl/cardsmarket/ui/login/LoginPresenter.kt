package com.kseniabl.cardsmarket.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.JsonObject
import javax.inject.Inject

import com.google.gson.JsonParser
import com.kseniabl.cardsmarket.ui.base.*
import com.kseniabl.cardsmarket.ui.models.CardModel
import com.kseniabl.cardsmarket.ui.models.ErrorModel
import com.kseniabl.cardsmarket.ui.models.UserModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


class LoginPresenter<V: LoginView, I: LoginInteractorInterface> @Inject constructor(var auth: FirebaseAuth, var context: Context, var interactor: I): BasePresenter<V>(), LoginPresenterInterface<V> {

    private fun createNewUser(name: String, email: String, password: String, passwordRep: String) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordRep.isEmpty())
            Toast.makeText(context, "Please, fill al fields", Toast.LENGTH_SHORT).show()
        else if (password != passwordRep)
            Toast.makeText(context, "Passwords doesn't match", Toast.LENGTH_SHORT).show()
        else {
            registrationWithServer(name, email, password)
        }
    }

    private fun registrationWithServer(name: String, email: String, password: String) {
        interactor.registrationApiCall(name, email, password).subscribe(object : Observer<JsonObject> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(data: JsonObject?) {
                if (data != null) {
                    val gson = Gson()
                    val parser = JsonParser()
                    val mJson = parser.parse(data.toString())
                    if (data.toString().contains("error")) {
                        val errorModel = gson.fromJson(mJson, ErrorModel::class.java)
                        Toast.makeText(context, errorModel.error, Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val userModel = gson.fromJson(mJson, UserModel::class.java)
                        getView()?.writeToken(userModel.token)
                        CurrentUser.setUser(userModel)
                        Toast.makeText(context, "You sing up successfully", Toast.LENGTH_SHORT).show()
                        getView()?.startMainActivity()
                    }
                }
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "registration onError ${e?.message}")
            }

            override fun onComplete() {
            }
        })
    }

    private fun loginWithServer(email: String, password: String) {
        interactor.loginApiCall(email, password).subscribe(object : Observer<JsonObject> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(data: JsonObject?) {
                if (data != null) {
                    val gson = Gson()
                    val parser = JsonParser()
                    val mJson = parser.parse(data.toString())
                    if (data.toString().contains("error")) {
                        val errorModel = gson.fromJson(mJson, ErrorModel::class.java)
                        Toast.makeText(context, errorModel.error, Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val userModel = gson.fromJson(mJson, UserModel::class.java)
                        getView()?.writeToken(userModel.token)
                        CurrentUser.setUser(userModel)
                        Toast.makeText(context, "You login successfully", Toast.LENGTH_SHORT).show()
                        if (CurrentUser.getUser()?.id != null)
                            loadAndSaveUsersCards(CurrentUser.getUser()!!.id)
                        getView()?.startMainActivity()
                    }
                }
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "login onError ${e?.message}")
            }

            override fun onComplete() {
            }
        })
    }

    private fun loadAndSaveUsersCards(id: String) {
        interactor.loadUserCards(id)
    }

    override fun createUser(name: String, email: String, password: String, passwordRep: String) {
        createNewUser(name, email, password, passwordRep)
    }

    override fun singIn(email: String, password: String) {
        loginWithServer(email, password)
    }
}