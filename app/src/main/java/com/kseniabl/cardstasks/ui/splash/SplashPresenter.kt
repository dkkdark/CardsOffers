package com.kseniabl.cardstasks.ui.splash

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.ui.base.*
import com.kseniabl.cardtasks.ui.splash.SplashPresenterInterface
import io.reactivex.rxjava3.core.FlowableSubscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class SplashPresenter<V: SplashView, I: SplashInteractorInterface> @Inject constructor(private var interactor: I, val currentUserClass: CurrentUserClass): BasePresenter<V>(),
    SplashPresenterInterface<V> {

    override fun loadData() {
        val token = currentUserClass.readSharedPref()?.token
        if (!token.isNullOrEmpty()) {
            isUserSaved(token)
            getView()?.openMainActivity()
        }
        else
            getView()?.openLoginActivity()
    }

    private fun isUserSaved(token: String) {
        interactor.isUserExist(token)
        .subscribe(object : FlowableSubscriber<UserModel> {
            override fun onSubscribe(p0: Subscription?) {
            }

            override fun onNext(data: UserModel?) {
                if (!data?.id.isNullOrEmpty()) {
                    if (currentUserClass.readSharedPref() == null || currentUserClass.readSharedPref()?.id.isNullOrEmpty())
                        currentUserClass.saveCurrentUser(data!!)
                    interactor.loadUserCards(currentUserClass.readSharedPref()!!.id)
                }
                setMessageToken()
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "splash onError ${e?.message}")
                setMessageToken()
            }

            override fun onComplete() {
            }
        })
    }

    private fun setMessageToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("qqq", "Fetching FCM registration token failed", task.exception)
                getView()?.openLoginActivity()
                return@OnCompleteListener
            }
            val messagingToken = task.result
            if (messagingToken != currentUserClass.readSharedPref()?.messagingToken && currentUserClass.readSharedPref() != null && currentUserClass.readSharedPref()?.id != "") {
                Log.e("qqq", "replace token in splash")
                interactor.changeToken(currentUserClass.readSharedPref()!!.messagingToken, messagingToken, currentUserClass.readSharedPref()!!.id)
                val user = currentUserClass.readSharedPref()
                user!!.messagingToken = messagingToken
                currentUserClass.saveCurrentUser(user)
            }
            else if (currentUserClass.readSharedPref() == null || currentUserClass.readSharedPref()?.id == "") {
                Log.e("qqq", "open login")
                getView()?.openLoginActivity()
            }
        })
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