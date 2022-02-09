package com.kseniabl.cardsmarket.ui.settings

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.UserCardInteractor
import javax.inject.Inject

class SettingsInteractor @Inject constructor(var context: Context): SettingsInteractorInterface, UserCardInteractor() {

    override fun singOutOfAccount(): Task<Void> {
        return AuthUI.getInstance()
            .signOut(context)
    }

    override fun getUserName(): Task<DataSnapshot>? {
        val userId = getUserId()
        if (userId != null) {
            return database.child("users").child(userId).child("profileInfo").child("name").get()
        }
        return null
    }

    override fun getUserEmail(): String? {
        return auth.currentUser?.email
    }

    override fun getProfession(): Task<DataSnapshot>? {
        val userId = getUserId()
        if (userId != null) {
            return database.child("users").child(userId).child("profileInfo").child("profession").get()
        }
        return null
    }

    override fun getAdditionalInfo(): Task<DataSnapshot>? {
        val userId = getUserId()
        if (userId != null) {
            return database.child("users").child(userId).child("profileInfo").child("additional").get()
        }
        return null
    }

    override fun setProfileName(name: String) {
        val userId = getUserId()
        if (userId != null) {
            database.child("users").child(userId).child("profileInfo").child("name").setValue(name)
        }
    }

    override fun setProfileProfessionField(spec: String, descr: String, tags: ArrayList<String>) {
        val userId = getUserId()
        if (userId != null) {
            database.child("users").child(userId).child("profileInfo").child("profession")
                .child("specialization").setValue(spec)
            database.child("users").child(userId).child("profileInfo").child("profession")
                .child("description").setValue(descr)
            database.child("users").child(userId).child("profileInfo").child("profession")
                .child("tags").setValue(tags)
        }
    }

    override fun setAdditionalInfoField(descr: String, country: String, city: String, type: String) {
        val userId = getUserId()
        if (userId != null) {
            database.child("users").child(userId).child("profileInfo").child("additional")
                .child("description").setValue(descr)
            database.child("users").child(userId).child("profileInfo").child("additional")
                .child("country").setValue(country)
            database.child("users").child(userId).child("profileInfo").child("additional")
                .child("city").setValue(city)
            database.child("users").child(userId).child("profileInfo").child("additional")
                .child("typeOfWork").setValue(type)
        }
    }
}