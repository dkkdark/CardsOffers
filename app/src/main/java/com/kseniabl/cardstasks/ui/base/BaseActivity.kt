package com.kseniabl.cardstasks.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.ui.base.BaseView
import dagger.android.AndroidInjection

abstract class BaseActivity: AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onResume() {
        super.onResume()
        CardTasksUtils.specifyActivityRun(this.javaClass.simpleName)
    }

    override fun hideLoadProgress() {
        TODO("Not yet implemented")
    }

    override fun showLoadProgress() {
        TODO("Not yet implemented")
    }
}