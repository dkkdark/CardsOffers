package com.kseniabl.cardtasks.ui.splash

import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface SplashPresenterInterface<V: SplashView>: PresenterInterface<V> {
    fun loadData()
}