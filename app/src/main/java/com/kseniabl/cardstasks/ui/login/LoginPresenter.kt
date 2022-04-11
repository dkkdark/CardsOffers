package com.kseniabl.cardstasks.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import javax.inject.Inject

import com.google.gson.JsonParser
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.CurrentUserClassInterface
import com.kseniabl.cardtasks.ui.models.ErrorModel
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.ui.login.LoginPresenterInterface
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class LoginPresenter<V: LoginView, I: LoginInteractorInterface> @Inject constructor(var context: Context, var interactor: I, var currentUserClass: CurrentUserClassInterface) :
    BasePresenter<V>(), LoginPresenterInterface<V> {

    private fun createNewUser(name: String, email: String, password: String, passwordRep: String) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordRep.isEmpty())
            Toast.makeText(context, "Please fill al fields", Toast.LENGTH_SHORT).show()
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
                        currentUserClass.saveCurrentUser(userModel)
                        Log.e("qqq", "2 current user = ${currentUserClass.readSharedPref()}")
                        Toast.makeText(context, "You sing up successfully", Toast.LENGTH_SHORT).show()
                        interactor.setToken()
                        getView()?.startMainActivity()
                    }
                }
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "registration onError ${e?.message}")
                Toast.makeText(context, "Server is not accessible. Try later", Toast.LENGTH_SHORT).show()
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
                        currentUserClass.saveCurrentUser(userModel)
                        Log.e("qqq", "3 current user = ${currentUserClass.readSharedPref()}")
                        Toast.makeText(context, "You login successfully", Toast.LENGTH_SHORT).show()
                        loadAndSaveUsersCards(currentUserClass.readSharedPref()!!.id)
                        interactor.setToken()

                        getView()?.startMainActivity()
                    }
                }
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "login onError ${e?.message}")
                Toast.makeText(context, "Server is not accessible. Try later", Toast.LENGTH_SHORT).show()
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