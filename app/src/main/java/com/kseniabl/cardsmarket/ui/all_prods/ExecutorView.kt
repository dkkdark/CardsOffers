package com.kseniabl.cardsmarket.ui.all_prods

import com.kseniabl.cardsmarket.ui.base.BaseView

interface ExecutorView: BaseView {
    fun provideAdapter(): ExecutorsAdapter
}