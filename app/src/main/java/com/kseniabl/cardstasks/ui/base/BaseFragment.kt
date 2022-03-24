package com.kseniabl.cardstasks.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.kseniabl.cardtasks.ui.base.BaseView
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment: Fragment(), BaseView {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun hideLoadProgress() {
        TODO("Not yet implemented")
    }

    override fun showLoadProgress() {
        TODO("Not yet implemented")
    }
}