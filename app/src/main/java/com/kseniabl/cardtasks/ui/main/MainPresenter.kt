package com.kseniabl.cardtasks.ui.main

import com.kseniabl.cardtasks.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter<V: MainView> @Inject constructor(): BasePresenter<V>(), MainPresenterInterface<V> {
}