package com.kseniabl.cardstasks.ui.all_prods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.all_prods.FreelancerPresenterCardModelInterface
import com.kseniabl.cardtasks.ui.all_prods.FreelancersAdapter
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardstasks.ui.main.MainActivity
import com.kseniabl.cardstasks.ui.models.UserModel
import kotlinx.android.synthetic.main.fragment_freelancers.*
import javax.inject.Inject
import javax.inject.Provider

class FreelancerFragment: BaseFragment(), FreelancerView {

    @Inject
    lateinit var presenter: FreelancerPresenterCardModelInterface<FreelancerView>
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>
    @Inject
    lateinit var adapter: FreelancersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_freelancers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFreelancers()
    }

    private fun setupRecyclerView() {
        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW;
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND;

        Freelancers_recycler_view.layoutManager = flexlayoutManager
        Freelancers_recycler_view.adapter = adapter
        Freelancers_recycler_view.setHasFixedSize(true)
        Freelancers_recycler_view.setItemViewCacheSize(20)
    }

    override fun provideAdapter(): FreelancersAdapter {
        return adapter
    }

    override fun loadFreelancerDetails(item: UserModel) {
        (activity as MainActivity).openFreelancerDetailsActivity(item)
    }

    companion object {
        fun newInstance(): FreelancerFragment = FreelancerFragment()
    }
}