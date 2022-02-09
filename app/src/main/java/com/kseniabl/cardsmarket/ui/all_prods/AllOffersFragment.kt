package com.kseniabl.cardsmarket.ui.all_prods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseFragment
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
        executorsButton.setOnClickListener {
            openExecutorsFragment()
        }
    }

    private fun openActiveTasksFragment() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(R.id.fragmentContainerAllOffers, AllProdsFragment.newInstance())
        }
    }

    private fun openExecutorsFragment() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(R.id.fragmentContainerAllOffers, ExecutorFragment.newInstance())
        }
    }

    companion object {
        fun newInstance(): AllOffersFragment = AllOffersFragment()
    }
}