package com.kseniabl.cardsmarket.ui.splash

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.kseniabl.cardsmarket.ui.all_prods.CardModel
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.UsersCards
import javax.inject.Inject

class SplashPresenter<V: SplashView, I: SplashInteractorInterface> @Inject constructor(private var interactor: I, private var auth: FirebaseAuth): BasePresenter<V>(), SplashPresenterInterface<V> {

    override fun attachView(view: V?) {
        super.attachView(view)
        openActivity()
    }

    private fun openActivity() {
        if (isLogin())
            getView()?.openMainActivity()
        else
            getView()?.openLoginActivity()

        loadAndSaveUsersCards()
    }

    private fun isLogin(): Boolean {
        return interactor.isUserLogin(auth)
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
}