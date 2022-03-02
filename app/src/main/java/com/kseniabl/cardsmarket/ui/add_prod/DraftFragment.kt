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
import com.kseniabl.cardsmarket.ui.dialogs.CreateNewTaskDialog
import com.kseniabl.cardsmarket.ui.models.CardModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.observeDataChange(adapter)

        childFragmentManager.setFragmentResultListener("CreateNewTaskDialog", this) { _, bundle ->
            val resId = bundle.getString("resId")
            val resTitle = bundle.getString("resTitle")
            val resDescription = bundle.getString("resDescription")
            val resActive = bundle.getBoolean("resActive")
            val resDate = bundle.getString("resDate")
            val resCost = bundle.getString("resCost")
            val resByAgreementValue = bundle.getBoolean("resByAgreementValue")
            val resCreateTime = bundle.getLong("resCreateTime")

            if (resId!= null && resTitle != null && resDescription != null && resDate != null && resCost != null && CurrentUser.getUser()?.id != null) {
                val cost = try {
                    resCost.toInt()
                } catch (e: NumberFormatException) {
                    0
                }

                presenter.changeUserCard(CurrentUser.getUser()!!.id, resId, resTitle, resDescription, resDate, resCreateTime, cost, resActive, resByAgreementValue)
            }
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

    override fun showCreateTaskDialog(item: CardModel) {
        val args = Bundle()
        args.putString("id", item.id)
        args.putString("title", item.title)
        args.putString("description", item.description)
        args.putInt("cost", item.cost)
        args.putString("date", item.date)
        args.putBoolean("active", item.active)
        args.putBoolean("agreement", item.agreement)
        args.putLong("createTime", item.createTime)
        val dialog = CreateNewTaskDialog()
        dialog.arguments = args
        dialog.show(childFragmentManager, "CreateNewTaskDialog")
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