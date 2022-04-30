package com.kseniabl.cardstasks.ui.freelancer_details

import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.ItemViewCardModel
import com.kseniabl.cardstasks.ui.models.CardModel
import javax.inject.Inject

class CardsFreelancerPresenter<V: CardsFreelancerView, I: CardsFreelancerInteractorInterface> @Inject constructor(val interactor: I): CardsFreelancerPresenterInterface<V>, BasePresenter<V>() {

    private val items = mutableListOf<CardModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, cardView: CardView) {
        val item = items[pos]
        getView()?.openShowItemFragment(item)
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

    override fun removeAllElements(list: List<CardModel>) {
        items.removeAll(list)
    }

    override fun loadCardsToRecycler(id: String, adapter: CardsFreelancerAdapter) {
        interactor.loadCards(id, adapter)
    }
}