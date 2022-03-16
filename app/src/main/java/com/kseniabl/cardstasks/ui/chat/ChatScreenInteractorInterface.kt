package com.kseniabl.cardtasks.ui.chat

import com.kseniabl.cardtasks.ui.base.BaseInteractor
import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface ChatScreenInteractorInterface: BaseInteractor {
    fun sendMessage(id: String, title: String, body: String)
}