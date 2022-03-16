package com.kseniabl.cardtasks.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import dagger.android.AndroidInjection

abstract class BaseActivity: AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun hideLoadProgress() {
        TODO("Not yet implemented")
    }

    override fun showLoadProgress() {
        TODO("Not yet implemented")
    }
}