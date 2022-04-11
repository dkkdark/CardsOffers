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
import kotlinx.android.synthetic.main.fragment_cards_freelancer.*
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cards_freelancer, container, false)
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
    }

    override fun onResume() {
        super.onResume()
        id?.let { presenter.loadCardsToRecycler(it, adapter) }
    }

    private fun loadCards() {
        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW;
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND;

        cardsFreelancerRecyclerView.layoutManager = flexlayoutManager
        cardsFreelancerRecyclerView.adapter = adapter
        cardsFreelancerRecyclerView.setHasFixedSize(true)
        cardsFreelancerRecyclerView.setItemViewCacheSize(20)
    }

    override fun openShowItemFragment(item: CardModel) {
        (activity as FreelancerDetailsActivity).openShowItemActivity(item)
    }
}