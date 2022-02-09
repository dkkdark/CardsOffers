package com.kseniabl.cardsmarket.ui.base

import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.kseniabl.cardsmarket.ui.splash.SplashInteractorInterface

abstract class BasePresenter<V: BaseView>: PresenterInterface<V> {

    private var view: V? = null

    override fun attachView(view: V?) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun getView(): V? {
        return view
    }
}