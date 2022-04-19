package com.kseniabl.cardstasks.ui.dialogs

import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.core.Flowable

interface CreateNewTaskInteractorInterface {
    fun deleteTaskFromServer(id: String, cardId: String): Flowable<MessageModel>
}