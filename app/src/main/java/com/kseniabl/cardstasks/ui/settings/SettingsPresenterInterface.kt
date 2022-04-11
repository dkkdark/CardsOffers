package com.kseniabl.cardtasks.ui.settings

import android.widget.CheckBox
import android.widget.TextView
import co.lujun.androidtagview.TagContainerLayout
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardstasks.ui.settings.SettingsView
import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface SettingsPresenterInterface<V: SettingsView>: PresenterInterface<V> {
    fun logoutUser()
    fun setupUserBaseInfo(name: TextView, ratingStarView: RatingStarView, checkBox: CheckBox, email: TextView)
    fun setupUserProfession(tags: TagContainerLayout, spec: TextView, descr: TextView)
    fun setupUserAdditionalInfo(descr: TextView, country: TextView, city: TextView, type: TextView)

    fun showUserName(name: TextView, username: String)
    fun showUserProfession(tagsLayout: TagContainerLayout, specText: TextView, descrText: TextView, spec: String, descr: String, tags: List<String>)
    fun showUserAdditionalInfo(descrText: TextView, countryText: TextView, cityText: TextView, typeText: TextView, descr: String, country: String, city: String, type: String)

    fun changeName(id: String, name: String)
    fun changeIsFreelancerState(id: String, state: Boolean)
    fun changeProfessionField(id: String, spec: String, descr: String, tags: ArrayList<String>)
    fun changeAdditionalInfo(id: String, descr: String, country: String, city: String, type: String)
}