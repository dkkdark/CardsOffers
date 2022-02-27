package com.kseniabl.cardsmarket.ui.add_prod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseFragment
import com.kseniabl.cardsmarket.ui.base.CurrentUser
import kotlinx.android.synthetic.main.fragment_draft.*
import javax.inject.Inject
import javax.inject.Provider

class DraftFragment: BaseFragment(), DraftView {

    @Inject
    lateinit var presenter: DraftPresenterInterface<DraftView>
    @Inject
    lateinit var adapter: DraftAdapter
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_draft, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (CurrentUser.getUser()?.id != null) {
            presenter.loadUserCards(CurrentUser.getUser()!!.id, adapter)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW;
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND;

        draftRecyclerView.layoutManager = flexlayoutManager
        draftRecyclerView.adapter = adapter
        draftRecyclerView.setHasFixedSize(true)
        draftRecyclerView.setItemViewCacheSize(20)

    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun provideAdapter(): DraftAdapter {
        return adapter
    }

    companion object {
        fun newInstance(): DraftFragment = DraftFragment()
    }
}