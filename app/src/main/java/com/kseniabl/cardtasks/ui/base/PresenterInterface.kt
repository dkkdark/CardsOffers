package com.kseniabl.cardtasks.ui.base

import android.view.View

interface PresenterInterface<V: BaseView> {
    fun attachView(view: V?)
    fun detachView()
    fun getView() : V?
}