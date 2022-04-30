package com.kseniabl.cardstasks.ui.freelancer_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardstasks.ui.main.MainActivity
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.databinding.FragmentActiveTasksBinding
import com.kseniabl.cardtasks.databinding.FragmentCardsFreelancerBinding
import javax.inject.Inject
import javax.inject.Provider

class CardsFreelancerFragment: BaseFragment(), CardsFreelancerView {

    @Inject
    lateinit var presenter: CardsFreelancerPresenterInterface<CardsFreelancerView>
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>
    @Inject
    lateinit var adapter: CardsFreelancerAdapter

    private var id: String? = null
    private var _binding: FragmentCardsFreelancerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCardsFreelancerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        id = arguments?.getString("id")

        loadCards()
    }

    companion object {
        fun newInstance(): CardsFreelancerFragment = CardsFreelancerFragment()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        id?.let { presenter.loadCardsToRecycler(it, adapter) }
    }

    private fun loadCards() {
        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW;
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND;

        binding.cardsFreelancerRecyclerView.apply {
            layoutManager = flexlayoutManager
            adapter = adapter
            setHasFixedSize(true)
            setItemViewCacheSize(20)
        }
    }

    override fun openShowItemFragment(item: CardModel) {
        (activity as FreelancerDetailsActivity).openShowItemActivity(item)
    }
}