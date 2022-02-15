package com.kseniabl.cardsmarket.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.kseniabl.cardsmarket.ui.all_prods.CardModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import android.R.string.no
import okhttp3.ResponseBody
import com.google.gson.JsonElement

import com.google.gson.JsonParser
import com.kseniabl.cardsmarket.ui.base.*


class LoginPresenter<V: LoginView, I: LoginInteractorInterface> @Inject constructor(var auth: FirebaseAuth, var context: Context, var interactor: I): BasePresenter<V>(), LoginPresenterInterface<V> {

    private fun createNewUser(name: String, email: String, password: String, passwordRep: String) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordRep.isEmpty())
            Toast.makeText(context, "Please, fill al fields", Toast.LENGTH_SHORT).show()
        else if (password != passwordRep)
            Toast.makeText(context, "Passwords doesn't match", Toast.LENGTH_SHORT).show()
        else {
            /*interactor.createUserWithEmail(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        interactor.setProfileInfo(name)

                        if (user != null) {
                            Toast.makeText(context, "Вы успешно зарегестрировались", Toast.LENGTH_SHORT).show()
                            getView()?.startMainActivity()
                        }
                        else
                            Toast.makeText(context, "Регестрация не выполнена", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Регестрация не выполнена", Toast.LENGTH_SHORT).show()
                    }
                }*/

                registrationWithServer(name, email, password)
        }
    }

    private fun createRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    private fun registrationWithServer(name: String, email: String, password: String) {
        val retrofit = createRetrofit()
        val api = retrofit.create(RetrofitApiHolder::class.java)
        val call = api.createUser(name, email, password)

        val gson = Gson()

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (!response.isSuccessful)
                    Log.e("ERROR_TAG", "Error: ${response.code()}")
                else {
                    if (response.body().toString().contains("errorTag")) {
                        val parser = JsonParser()
                        val mJson = parser.parse(response.body().toString())
                        val error = gson.fromJson(mJson, ErrorModel::class.java)
                        Toast.makeText(context, error.errorTag, Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, "You sing up successfully", Toast.LENGTH_SHORT).show()
                        getView()?.startMainActivity()
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR_TAG", "Error: ${t.message}")
            }
        })
    }

    private fun loginWithServer(email: String, password: String) {
        val retrofit = createRetrofit()
        val api = retrofit.create(RetrofitApiHolder::class.java)
        val call = api.checkLogin(email, password)

        val gson = Gson()

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (!response.isSuccessful)
                    Log.e("ERROR_TAG", "Error: ${response.code()}")
                else {
                    val parser = JsonParser()
                    val mJson = parser.parse(response.body().toString())
                    val loginModel = gson.fromJson(mJson, LoginModel::class.java)
                    if (!loginModel.loginOrNot) {
                        Toast.makeText(context, "This user doesn't exist", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, "You login successfully", Toast.LENGTH_SHORT).show()
                        getView()?.startMainActivity()
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR_TAG", "Error: ${t.message}")
            }
        })
    }

    private fun signInUser(email: String, password: String){
        loginWithServer(email, password)
        /*if (email.isEmpty() || password.isEmpty())
            Toast.makeText(context, "Please, fill al fields", Toast.LENGTH_SHORT).show()
        else {
            interactor.singInWithEmail(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        getView()?.startMainActivity()
                        loadAndSaveUsersCards()
                    } else {
                        Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }*/
    }

    private fun loadAndSaveUsersCards() {
        val cards = arrayListOf<CardModel>()

        interactor.getCards()?.let {
            it.addOnSuccessListener {
                for (el in it.children) {
                    if (el.key?.equals("profileInfo") == false) {
                        val newCard = CardModel(el.key.toString(), el.child("title").value.toString(), el.child("description").value.toString(),
                            el.child("active").value as Boolean, el.child("date").value.toString(), el.child("cost").value.toString(),
                            el.child("agreement").value as Boolean, el.child("createTime").value as Long)
                        cards.add(newCard)
                    }
                }
                UsersCards.setCards(cards)
            }
            it.addOnFailureListener {
                Log.e("CreateProdInteractorError", "Something went wrong: " + it.message)
            }
        }
    }

    override fun createUser(name: String, email: String, password: String, passwordRep: String) {
        createNewUser(name, email, password, passwordRep)
    }

    override fun singIn(email: String, password: String) {
        signInUser(email, password)
    }
}