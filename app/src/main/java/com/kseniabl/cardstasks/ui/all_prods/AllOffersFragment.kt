package com.kseniabl.cardstasks.ui.all_prods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.all_prods.AllOffersPresenterInterface
import com.kseniabl.cardtasks.ui.all_prods.AllOffersView
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardtasks.databinding.FragmentActiveTasksBinding
import com.kseniabl.cardtasks.databinding.FragmnetAllOffersBinding
import javax.inject.Inject

class AllOffersFragment: BaseFragment(), AllOffersView {

    @Inject
    lateinit var presenter: AllOffersPresenterInterface<AllOffersView>

    private var _binding: FragmnetAllOffersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmnetAllOffersBinding.inflate(inflater, container, false)
        return binding.root
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
        _binding = null
    }

    private fun setTopButtonsClickListeners() {
        binding.activeTasksButton.setOnClickListener {
            openActiveTasksFragment()
        }
        binding.freelancersButton.setOnClickListener {
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