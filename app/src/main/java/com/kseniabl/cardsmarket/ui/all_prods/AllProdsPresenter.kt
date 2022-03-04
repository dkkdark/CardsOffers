package com.kseniabl.cardsmarket.ui.all_prods

import android.util.Log
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.ItemViewCardModel
import com.kseniabl.cardsmarket.ui.models.CardModel
import javax.inject.Inject

class AllProdsPresenter<V: AllProdsView, I: AllProdsInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), AllProdsPresenterCardModelInterface<V> {

    private val items = mutableListOf<CardModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, cardView: CardView) {
        val item = items[pos]
        getView()?.openShowItemFragment(item, cardView)
    }

    override fun onBindItemView(itemViewCardModel: ItemViewCardModel, pos: Int) {
        itemViewCardModel.bindItem(items[pos])
    }

    override fun addElementsToList(list: List<CardModel>) {
        items.addAll(list)
    }

    override fun addElementToList(el: CardModel, pos: Int) {
        items.add(pos, el)
    }

    override fun removeElementFromList(el: CardModel) {
        items.remove(el)
    }

    override fun getAllElements(): MutableList<CardModel> {
        return items
    }

    override fun getPos(el: CardModel): Int {
        return items.indexOf(el)
    }

    override fun loadAllCards(adapter: AllProdsAdapter) {
        interactor.loadCards(adapter)
    }
}