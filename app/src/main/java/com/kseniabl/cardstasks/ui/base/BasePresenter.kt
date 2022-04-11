package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.ui.base.BaseView
import com.kseniabl.cardtasks.ui.base.PresenterInterface

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