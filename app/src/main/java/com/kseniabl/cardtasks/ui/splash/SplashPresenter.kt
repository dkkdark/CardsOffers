package com.kseniabl.cardtasks.ui.splash

import com.kseniabl.cardtasks.ui.base.*
import javax.inject.Inject

class SplashPresenter<V: SplashView, I: SplashInteractorInterface> @Inject constructor(private var interactor: I): BasePresenter<V>(), SplashPresenterInterface<V> {

    override fun loadData() {
        val token = getView()?.readToken()
        if (token != null && token != "") {
            interactor.isUserLogin(token)
            getView()?.openMainActivity()
        }
        else
            getView()?.openLoginActivity()
    }

    private fun loadAndSaveUsersCards(id: String) {
        interactor.loadUserCards(id)

        /*val cards = arrayListOf<CardModel>()

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
        }*/
    }
}