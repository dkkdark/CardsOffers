package com.kseniabl.cardsmarket.ui.settings

import android.widget.TextView
import co.lujun.androidtagview.TagContainerLayout
import co.lujun.androidtagview.TagView
import com.kseniabl.cardsmarket.ui.base.PresenterInterface

interface SettingsPresenterInterface<V: SettingsView>: PresenterInterface<V> {
    fun logoutUser()
    fun showUserName(textName: TextView)
    fun showUserEmail(textView: TextView)
    fun showUserProfession(tags: TagContainerLayout, spec: TextView, descr: TextView)
    fun showUserAdditionalInfo(descr: TextView, country: TextView, city: TextView, type: TextView)
    fun changeName(name: String)
    fun changeProfessionField(spec: String, descr: String, tags: ArrayList<String>)
    fun changeAdditionalInfo(descr: String, country: String, city: String, type: String)
}