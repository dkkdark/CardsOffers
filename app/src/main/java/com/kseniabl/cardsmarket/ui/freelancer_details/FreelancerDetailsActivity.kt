package com.kseniabl.cardsmarket.ui.freelancer_details

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.commit
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseActivity
import com.kseniabl.cardsmarket.ui.main.MainActivity
import com.kseniabl.cardsmarket.ui.settings.SettingsFragmnet
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_details_freelancer.*
import javax.inject.Inject

class FreelancerDetailsActivity: BaseActivity(), FreelancerDetailsView, HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var presenter: FreelancerDetailsPresenterInterface<FreelancerDetailsView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_freelancer)
        presenter.attachView(this)

        addListeners()
        openInfoFreelancer()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("whatFragment", "freelancerFragment")
        startActivity(intent)
        super.onBackPressed()
    }

    private fun openInfoFreelancer() {
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            setReorderingAllowed(true)
            replace(R.id.fragment_details_activity, InfoFreelancerFragment.newInstance())
        }
    }

    private fun openCardsFreelancer() {
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            setReorderingAllowed(true)
            replace(R.id.fragment_details_activity, CardsFreelancerFragment.newInstance())
        }
    }

    private fun addListeners() {
        infoButton.setOnClickListener { openInfoFreelancer() }
        cardsButton.setOnClickListener { openCardsFreelancer() }
    }
}