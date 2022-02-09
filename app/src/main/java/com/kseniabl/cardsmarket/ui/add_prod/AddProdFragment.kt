package com.kseniabl.cardsmarket.ui.add_prod

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.commit
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.all_prods.CardModel
import com.kseniabl.cardsmarket.ui.base.BaseFragment
import com.kseniabl.cardsmarket.ui.create_prod.CreateProdActivity
import com.kseniabl.cardsmarket.ui.dialogs.ChangeNameDialogFragment
import com.kseniabl.cardsmarket.ui.dialogs.CreateNewTaskDialog
import com.kseniabl.cardsmarket.ui.show_item.ShowItemFragment
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_active.*
import kotlinx.android.synthetic.main.fragment_add_card.*
import javax.inject.Inject
import javax.inject.Provider


class AddProdFragment: BaseFragment(), AddProdView {

    @Inject
    lateinit var presenter: AddProdPresenterCardModelInterface<AddProdView>
    @Inject
    lateinit var adapter: AddProdsAdapter
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_active, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadBaseCards()
        presenter.loadAddedCards()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW;
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND;

        addCardRecyclerView.layoutManager = flexlayoutManager
        addCardRecyclerView.adapter = adapter
        addCardRecyclerView.setHasFixedSize(true)
        addCardRecyclerView.setItemViewCacheSize(20)

    }

    override fun startTransition() {
        postponeEnterTransition()
        (view?.parent as ViewGroup).doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun provideAdapter(): AddProdsAdapter {
        return adapter
    }

    override fun showCreateTaskDialog() {
        val args = Bundle()
        //args.putString("name", profileNameText.text.toString())
        val dialog = CreateNewTaskDialog()
        dialog.arguments = args
        dialog.show(childFragmentManager, "CreateNewTaskDialog")
    }

    override fun openShowItemFragment(item: CardModel, image: CardView) {
        val fragment = ShowItemFragment.newInstance()
        val args = Bundle()
        //args.putString("img", item.img)
        //args.putString("name", item.name)
        args.putString("descr", item.description)
        fragment.arguments = args

        parentFragmentManager.commit {
            setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
            addSharedElement(image, "itemProdContainer")
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
        }
    }

    companion object {
        fun newInstance(): AddProdFragment = AddProdFragment()
    }
}