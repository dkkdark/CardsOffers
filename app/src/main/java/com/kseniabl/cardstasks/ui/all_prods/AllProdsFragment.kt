package com.kseniabl.cardstasks.ui.all_prods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardstasks.db.view_models.AllCardsViewModel
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardstasks.ui.main.MainActivity
import com.kseniabl.cardtasks.ui.all_prods.AllProdsView
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.databinding.FragmentActiveTasksBinding
import javax.inject.Inject
import javax.inject.Provider

class AllProdsFragment: BaseFragment(), AllProdsView {

    @Inject
    lateinit var presenter: AllProdsPresenterCardModelInterface<AllProdsView>
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>
    @Inject
    lateinit var allProdsAdapter: AllProdsAdapter

    private var _binding: FragmentActiveTasksBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentActiveTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.loadAllCards(allProdsAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND

        binding.allProdsRecyclerView.apply {
            layoutManager = flexlayoutManager
            adapter = allProdsAdapter
            setHasFixedSize(true)
            setItemViewCacheSize(20)
        }

        val viewModel = ViewModelProvider(this, viewModelProviderFactory)[AllCardsViewModel::class.java]
        viewModel.allCards.observe(viewLifecycleOwner) { res ->
            if (res.data != null) {
                val list = mutableListOf<CardModel>()
                for (el in res.data) {
                    list.addAll(el.cards)
                }
                allProdsAdapter.addElements(list.reversed())
            }

        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
        _binding = null
    }

    override fun provideAdapter(): AllProdsAdapter {
        return allProdsAdapter
    }

    override fun openShowItemFragment(item: CardModel, cardView: CardView) {
        (activity as MainActivity).openShowItemActivity(item, cardView)
    }

    companion object {
        fun newInstance(): AllProdsFragment = AllProdsFragment()
    }
}