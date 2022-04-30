package com.kseniabl.cardstasks.ui.splash

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.ui.splash.SplashPresenterInterface
import io.reactivex.rxjava3.core.FlowableSubscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class SplashPresenter<V: SplashView, I: SplashInteractorInterface> @Inject constructor(private var interactor: I, val currentUserClass: CurrentUserClass): BasePresenter<V>(),
    SplashPresenterInterface<V> {

    override fun loadData() {
        val token = currentUserClass.readSharedPref()?.token
        Log.e("qqq", "messaging token = ${currentUserClass.readSharedPref()?.messagingToken}")
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
            override fun onSubscribe(s: Subscription?) {
                s?.request(Long.MAX_VALUE)
            }

            override fun onNext(data: UserModel?) {
                if (!data?.id.isNullOrEmpty()) {
                    if (currentUserClass.readSharedPref() == null || currentUserClass.readSharedPref()?.id.isNullOrEmpty())
                        currentUserClass.saveCurrentUser(data!!)
                    //interactor.loadUserCards(currentUserClass.readSharedPref()!!.id)
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
            if (messagingToken != currentUserClass.readSharedPref()?.messagingToken && (currentUserClass.readSharedPref() != null || currentUserClass.readSharedPref()?.id.isNullOrEmpty())) {
                Log.e("qqq", "replace token in splash")
                if (!currentUserClass.readSharedPref()?.messagingToken.isNullOrEmpty())
                    interactor.changeToken(currentUserClass.readSharedPref()!!.messagingToken, messagingToken, currentUserClass.readSharedPref()!!.id)
                else {
                    Log.e("qqq", "1 open login")
                    getView()?.openLoginActivity()
                    return@OnCompleteListener
                }

                val user = currentUserClass.readSharedPref()
                user!!.messagingToken = messagingToken
                currentUserClass.saveCurrentUser(user)
            }
            else if (currentUserClass.readSharedPref() == null || currentUserClass.readSharedPref()?.id == "") {
                Log.e("qqq", "2 open login")
                getView()?.openLoginActivity()
            }
        })
    }

}