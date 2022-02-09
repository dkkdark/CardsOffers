package com.kseniabl.cardsmarket.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.kseniabl.cardsmarket.ui.all_prods.CardModel
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.UsersCards
import javax.inject.Inject

class LoginPresenter<V: LoginView, I: LoginInteractorInterface> @Inject constructor(var auth: FirebaseAuth, var context: Context, var interactor: I): BasePresenter<V>(), LoginPresenterInterface<V> {

    private fun createNewUser(name: String, email: String, password: String, passwordRep: String) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordRep.isEmpty())
            Toast.makeText(context, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
        else if (password != passwordRep)
            Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
        else {
            interactor.createUserWithEmail(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        val additionalInfo = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()

                        if (user != null) {
                            user.updateProfile(additionalInfo)
                            Toast.makeText(context, "Вы успешно зарегестрировались", Toast.LENGTH_SHORT).show()
                            getView()?.startMainActivity()
                        }
                        else
                            Toast.makeText(context, "Регестрация не выполнена", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Регестрация не выполнена", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun signInUser(email: String, password: String){
        if (email.isEmpty() || password.isEmpty())
            Toast.makeText(context, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
        else {
            interactor.singInWithEmail(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        getView()?.startMainActivity()
                        loadAndSaveUsersCards()
                    } else {
                        Toast.makeText(context, "Вход не выполнен", Toast.LENGTH_SHORT).show()
                    }
                }
        }
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