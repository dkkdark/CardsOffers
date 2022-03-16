package com.kseniabl.cardtasks.ui.all_prods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragmnet_all_offers.*
import javax.inject.Inject

class AllOffersFragment: BaseFragment(), AllOffersView {

    @Inject
    lateinit var presenter: AllOffersPresenterInterface<AllOffersView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmnet_all_offers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        val fragment = arguments?.getString("fragment")
        if (fragment == "freelancerFragment")
            openFreelancersFragment()
        else
            openActiveTasksFragment()
        setTopButtonsClickListeners()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun setTopButtonsClickListeners() {
        activeTasksButton.setOnClickListener {
            openActiveTasksFragment()
        }
        freelancersButton.setOnClickListener {
            openFreelancersFragment()
        }
    }

    private fun openActiveTasksFragment() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerAllOffers) as NavHostFragment
        navHostFragment.navController.navigate(R.id.allProdsFragment)
    }

    private fun openFreelancersFragment() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerAllOffers) as NavHostFragment
        navHostFragment.navController.navigate(R.id.freelancerFragment)
    }

    companion object {
        fun newInstance(): AllOffersFragment = AllOffersFragment()
    }
}