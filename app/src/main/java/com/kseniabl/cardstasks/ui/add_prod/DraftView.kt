package com.kseniabl.cardtasks.ui.add_prod

import com.kseniabl.cardtasks.ui.base.BaseView
import com.kseniabl.cardstasks.ui.models.CardModel

interface DraftView: BaseView {
    fun showCreateTaskDialog(item: CardModel)
    fun provideAdapter(): DraftAdapter
}