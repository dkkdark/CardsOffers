package com.kseniabl.cardtasks.ui.add_prod

import com.kseniabl.cardtasks.ui.base.BaseView
import com.kseniabl.cardtasks.ui.models.CardModel

interface AddProdView: BaseView {
    fun provideAdapter(): AddProdsAdapter
    fun showCreateTaskDialog(item: CardModel)
    fun startTransition()
}