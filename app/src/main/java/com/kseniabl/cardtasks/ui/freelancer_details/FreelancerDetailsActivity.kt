package com.kseniabl.cardtasks.ui.freelancer_details

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.base.BaseActivity
import com.kseniabl.cardtasks.ui.chat.ChatScreenActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_details_freelancer.*
import javax.inject.Inject

class FreelancerDetailsActivity: BaseActivity(), FreelancerDetailsView, HasAndroidInjector {

    @Inject
    lateinit var presenter: FreelancerDetailsPresenterInterface<FreelancerDetailsView>
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_freelancer)
        presenter.attachView(this)

        addListeners()
        openInfoFreelancer()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    private fun openInfoFreelancer() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_details_activity) as NavHostFragment
        navHostFragment.navController.navigate(R.id.infoFreelancerFragment3)
    }

    private fun openCardsFreelancer() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_details_activity) as NavHostFragment
        navHostFragment.navController.navigate(R.id.cardsFreelancerFragment3)
    }

    private fun addListeners() {
        infoButton.setOnClickListener { openInfoFreelancer() }
        cardsButton.setOnClickListener { openCardsFreelancer() }
    }

    override fun openChatScreenActivity() {
        val intent = Intent(this, ChatScreenActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
    }
}