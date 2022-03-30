package com.kseniabl.cardstasks.ui.base

import android.app.Activity
import android.app.Application
import android.content.Context
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.R
import javax.inject.Inject
import com.google.gson.Gson

class CurrentUserClass @Inject constructor(var context: Context): CurrentUserClassInterface {

    override fun readSharedPref(): UserModel? {
        val sharedPref = context.getSharedPreferences("currentUserSave", Context.MODE_PRIVATE)
        val json = sharedPref.getString(context.getString(R.string.current_user_shared_pref), "")
        val gson = Gson()
        return try {
            gson.fromJson(json, UserModel::class.java)
        } catch (e: NullPointerException) {
            null
        }
    }

    override fun saveCurrentUser(currentUser: UserModel) {
        val gson = Gson()
        val json = gson.toJson(currentUser)
        val sharedPref = context.getSharedPreferences("currentUserSave", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(context.getString(R.string.current_user_shared_pref), json)
            apply()
        }
    }
}