package com.kseniabl.cardstasks.ui.dialogs

import android.content.DialogInterface

interface CreateNewTaskPresenterInterface {
    fun deleteTask(dialog: CreateNewTaskDialog, alert: DialogInterface, id: String, cardId: String)
}