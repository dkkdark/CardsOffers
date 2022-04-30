package com.kseniabl.cardstasks.ui.freelancer_details

import android.content.Intent
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.NavHostFragment
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseActivity
import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardstasks.ui.main.MainActivity
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardstasks.ui.show_item.ShowItemActivity
import com.kseniabl.cardtasks.databinding.ActivityDetailsFreelancerBinding
import com.kseniabl.cardtasks.databinding.ActivityMainBinding
import com.kseniabl.cardtasks.ui.freelancer_details.FreelancerDetailsPresenterInterface
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class FreelancerDetailsActivity: BaseActivity(), FreelancerDetailsView, HasAndroidInjector {

    @Inject
    lateinit var presenter: FreelancerDetailsPresenterInterface<FreelancerDetailsView>
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private var freelancer: FreelancerModel? = null
    private lateinit var binding: ActivityDetailsFreelancerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsFreelancerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attachView(this)

        freelancer = intent.extras?.getSerializable("item") as FreelancerModel
        binding.freelancerNameText.text = freelancer?.username

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
        val bundle = Bundle()
        bundle.putString("id", freelancer?.id)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_details_activity) as NavHostFragment
        navHostFragment.navController.navigate(R.id.cardsFreelancerFragment, bundle)
    }

    private fun addListeners() {
        binding.apply {
            infoButton.setOnClickListener { openInfoFreelancer() }
            cardsButton.setOnClickListener { openCardsFreelancer() }
        }
    }

    fun openShowItemActivity(card: CardModel) {
        val intent = Intent(this, ShowItemActivity::class.java)
        intent.putExtra("card", card)
        startActivity(intent)
    }
}