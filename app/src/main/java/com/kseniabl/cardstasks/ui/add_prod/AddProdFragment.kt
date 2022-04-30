package com.kseniabl.cardstasks.ui.add_prod

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardstasks.db.view_models.AddProdViewModel
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardstasks.ui.base.CurrentUserClassInterface
import com.kseniabl.cardtasks.ui.add_prod.AddProdsAdapter
import com.kseniabl.cardstasks.ui.dialogs.CreateNewTaskDialog
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.databinding.FragmentActiveBinding
import javax.inject.Inject
import javax.inject.Provider

class AddProdFragment: BaseFragment(), AddProdView {

    @Inject
    lateinit var presenter: AddProdPresenterCardModelInterface<AddProdView>
    @Inject
    lateinit var addProdsAdapter: AddProdsAdapter
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>
    @Inject
    lateinit var currentUserClass: CurrentUserClassInterface

    private var _binding: FragmentActiveBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentActiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        //currentUserClass.readSharedPref()?.id?.let { presenter.loadUserCards(it, addProdsAdapter) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND

        binding.apply {
            addCardRecyclerView.layoutManager = flexlayoutManager
            addCardRecyclerView.adapter = addProdsAdapter
            addCardRecyclerView.setHasFixedSize(true)
            addCardRecyclerView.setItemViewCacheSize(20)
        }

        val viewModel = ViewModelProvider(this, viewModelProviderFactory)[AddProdViewModel::class.java]
        viewModel.allCards.observe(viewLifecycleOwner) {
            presenter.listToDB(it)
            addProdsAdapter.addElements(it.reversed())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //presenter.observeDataChange(addProdsAdapter)

        childFragmentManager.setFragmentResultListener("CreateNewTaskDialog", this) { _, bundle ->
            val resId = bundle.getString("resId")
            val resTitle = bundle.getString("resTitle")
            val resDescription = bundle.getString("resDescription")
            val resActive = bundle.getBoolean("resActive")
            val resDate = bundle.getString("resDate")
            val resCost = bundle.getString("resCost")
            val resByAgreementValue = bundle.getBoolean("resByAgreementValue")
            val resCreateTime = bundle.getLong("resCreateTime")

            if (resId!= null && resTitle != null && resDescription != null && resDate != null && resCost != null) {
                val cost = try {
                    resCost.toInt()
                } catch (e: NumberFormatException) {
                    0
                }

                currentUserClass.readSharedPref()?.id?.let { presenter.changeUserCard(it, resId, resTitle, resDescription, resDate, resCreateTime, cost, resActive, resByAgreementValue) }
            }
        }

        childFragmentManager.setFragmentResultListener("deleteResultCreateTask", this) { _, bundle ->
            val userId = bundle.getString("userId")
            val cardId = bundle.getString("cardId")
            if (userId != null && cardId != null)
                presenter.deleteCard(userId, cardId)
        }
    }

    override fun startTransition() {
        postponeEnterTransition()
        (view?.parent as ViewGroup).doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
        _binding = null
    }

    override fun provideAdapter(): AddProdsAdapter {
        return addProdsAdapter
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
        args.putString("userId", item.user_id)
        val dialog = CreateNewTaskDialog()
        dialog.arguments = args
        dialog.show(childFragmentManager, "CreateNewTaskDialog")
    }

    companion object {
        fun newInstance(): AddProdFragment = AddProdFragment()
    }
}