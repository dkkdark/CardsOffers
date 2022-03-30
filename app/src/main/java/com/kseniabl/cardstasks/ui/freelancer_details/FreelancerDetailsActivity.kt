package com.kseniabl.cardstasks.ui.freelancer_details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseActivity
import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardstasks.ui.chat.ChatScreenActivity
import com.kseniabl.cardstasks.ui.main.MainActivity
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.ui.freelancer_details.FreelancerDetailsPresenterInterface
import com.kseniabl.cardtasks.ui.freelancer_details.FreelancerDetailsView
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

    private var freelancer: FreelancerModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_freelancer)
        presenter.attachView(this)

        freelancer = intent.extras?.getSerializable("item") as FreelancerModel
        freelancerNameText.text = freelancer?.username

        addListeners()
        openInfoFreelancer()
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

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    private fun openInfoFreelancer() {
        val bundle = Bundle()
        bundle.putSerializable("item", freelancer)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_details_activity) as NavHostFragment
        navHostFragment.navController.navigate(R.id.infoFreelancerFragment, bundle)
    }

    private fun openCardsFreelancer() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_details_activity) as NavHostFragment
        navHostFragment.navController.navigate(R.id.cardsFreelancerFragment)
    }

    private fun addListeners() {
        infoButton.setOnClickListener { openInfoFreelancer() }
        cardsButton.setOnClickListener { openCardsFreelancer() }
    }

    override fun openChatScreenActivity() {
        val intent = Intent(this, ChatScreenActivity::class.java)
        intent.putExtra("id", freelancer?.id)
        startActivity(intent)
    }
}