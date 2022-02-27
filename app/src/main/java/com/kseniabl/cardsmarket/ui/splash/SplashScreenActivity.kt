package com.kseniabl.cardsmarket.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.main.MainActivity
import com.kseniabl.cardsmarket.ui.base.BaseActivity
import com.kseniabl.cardsmarket.ui.base.CurrentUser
import com.kseniabl.cardsmarket.ui.login.LoginActivity
import javax.inject.Inject

class SplashScreenActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var presenter: SplashPresenterInterface<SplashView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadData()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showLoadProgress() {
        TODO("Not yet implemented")
    }

    override fun hideLoadProgress() {
        TODO("Not yet implemented")
    }

    override fun readToken(): String? {
        val sharedPref = getSharedPreferences("tokenSave", Context.MODE_PRIVATE)
        return sharedPref.getString(getString(R.string.token_shared_pref), "")
    }
}