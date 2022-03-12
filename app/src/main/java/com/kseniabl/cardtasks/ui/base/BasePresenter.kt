package com.kseniabl.cardtasks.ui.base

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