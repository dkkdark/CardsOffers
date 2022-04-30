package com.kseniabl.cardstasks.ui.all_prods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.all_prods.FreelancersAdapter
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardstasks.ui.main.MainActivity
import com.kseniabl.cardtasks.databinding.FragmentActiveTasksBinding
import com.kseniabl.cardtasks.databinding.FragmentFreelancersBinding
import javax.inject.Inject
import javax.inject.Provider

class FreelancerFragment: BaseFragment(), FreelancerView {

    @Inject
    lateinit var presenter: FreelancerPresenterCardModelInterface<FreelancerView>
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>
    @Inject
    lateinit var adapter: FreelancersAdapter

    private var _binding: FragmentFreelancersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFreelancersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFreelancers()
    }

    private fun setupRecyclerView() {
        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW;
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND;

        binding.apply {
            freelancersRecyclerView.layoutManager = flexlayoutManager
            freelancersRecyclerView.adapter = adapter
            freelancersRecyclerView.setHasFixedSize(true)
            freelancersRecyclerView.setItemViewCacheSize(20)
        }
    }

    override fun provideAdapter(): FreelancersAdapter {
        return adapter
    }

    override fun loadFreelancerDetails(item: FreelancerModel) {
        (activity as MainActivity).openFreelancerDetailsActivity(item)
    }

    companion object {
        fun newInstance(): FreelancerFragment = FreelancerFragment()
    }
}