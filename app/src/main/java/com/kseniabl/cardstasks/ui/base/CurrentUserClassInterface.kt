package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.ui.models.UserModel

interface CurrentUserClassInterface {
    fun readSharedPref(): UserModel
    fun saveCurrentUser(currentUser: UserModel)
}