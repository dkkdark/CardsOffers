package com.kseniabl.cardtasks.ui.main

import com.kseniabl.cardstasks.ui.main.MainView
import com.kseniabl.cardstasks.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter<V: MainView> @Inject constructor(): BasePresenter<V>(), MainPresenterInterface<V> {
}