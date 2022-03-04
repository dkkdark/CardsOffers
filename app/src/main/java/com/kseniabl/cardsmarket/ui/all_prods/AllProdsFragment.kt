package com.kseniabl.cardsmarket.ui.all_prods

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.commit
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseFragment
import com.kseniabl.cardsmarket.ui.base.UsersCards
import com.kseniabl.cardsmarket.ui.login.LoginActivity
import com.kseniabl.cardsmarket.ui.main.MainActivity
import com.kseniabl.cardsmarket.ui.models.CardModel
import com.kseniabl.cardsmarket.ui.show_item.ShowItemActivity
import kotlinx.android.synthetic.main.fragment_active_tasks.*
import javax.inject.Inject
import javax.inject.Provider

class AllProdsFragment: BaseFragment(), AllProdsView {

    @Inject
    lateinit var presenter: AllProdsPresenterCardModelInterface<AllProdsView>
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>
    @Inject
    lateinit var adapter: AllProdsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_active_tasks, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadAllCards(adapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW;
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND;

        all_prods_recycler_view.layoutManager = flexlayoutManager
        all_prods_recycler_view.adapter = adapter
        all_prods_recycler_view.setHasFixedSize(true)
        all_prods_recycler_view.setItemViewCacheSize(20)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun provideAdapter(): AllProdsAdapter {
        return adapter
    }

    override fun openShowItemFragment(item: CardModel, cardView: CardView) {
        (activity as MainActivity).openShowItemActivity(item, cardView)
    }

    companion object {
        fun newInstance(): AllProdsFragment = AllProdsFragment()
    }
}