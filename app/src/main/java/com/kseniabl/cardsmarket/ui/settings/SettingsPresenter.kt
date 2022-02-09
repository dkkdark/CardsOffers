package com.kseniabl.cardsmarket.ui.settings

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import co.lujun.androidtagview.TagContainerLayout
import co.lujun.androidtagview.TagView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.UsersCards
import org.w3c.dom.Text
import javax.inject.Inject

class SettingsPresenter<V: SettingsView, I: SettingsInteractorInterface> @Inject constructor(var context: Context, var interactor: I): BasePresenter<V>(), SettingsPresenterInterface<V> {

    private fun singOut() {
        interactor.singOutOfAccount()
            .addOnCompleteListener { getView()?.openLoginActivity() }
            .addOnCanceledListener { Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show() }
    }

    override fun logoutUser() {
        singOut()
        UsersCards.clearCards()
    }

    override fun showUserName(textName: TextView) {
        interactor.getUserName()?.addOnSuccessListener { textName.text = it.value.toString() }
    }

    override fun showUserEmail(textView: TextView) {
        interactor.getUserEmail()?.let { textView.text = it }
    }

    override fun showUserProfession(tags: TagContainerLayout, spec: TextView, descr: TextView) {
        interactor.getProfession()?.addOnSuccessListener {
            val tagList = arrayListOf<String>()
            for (el in it.child("tags").children) {
                tagList.add(el.value.toString())
            }
            tags.tags = tagList
            if (it.child("specialization").value != null && it.child("specialization").value != "")
                spec.text = it.child("specialization").value.toString()
            else
                spec.text = "—"
            if (it.child("description").value != null && it.child("description").value != "")
                descr.text = it.child("description").value.toString()
            else
                descr.text = "—"
        }
    }

    override fun showUserAdditionalInfo(descr: TextView, country: TextView, city: TextView, type: TextView) {
        interactor.getAdditionalInfo()?.addOnSuccessListener {
            if (it.child("description").value != null && it.child("description").value != "")
                descr.text = it.child("description").value.toString()
            else
                descr.text = "—"
            if (it.child("country").value != null && it.child("country").value != "")
                country.text = it.child("country").value.toString()
            else
                country.text = "—"
            if (it.child("city").value != null && it.child("city").value != "")
                city.text = it.child("city").value.toString()
            else
                city.text = "—"
            if (it.child("typeOfWork").value != null && it.child("typeOfWork").value != "")
                type.text = it.child("typeOfWork").value.toString()
            else
                type.text = "—"
        }
    }

    override fun changeName(name: String) {
        interactor.setProfileName(name)
    }

    override fun changeProfessionField(spec: String, descr: String, tags: ArrayList<String>) {
        interactor.setProfileProfessionField(spec, descr, tags)
    }

    override fun changeAdditionalInfo(descr: String, country: String, city: String, type: String) {
        interactor.setAdditionalInfoField(descr, country, city, type)
    }
}