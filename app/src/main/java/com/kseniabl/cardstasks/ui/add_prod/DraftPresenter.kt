package com.kseniabl.cardtasks.ui.add_prod

import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.ItemViewCardModel
import com.kseniabl.cardstasks.ui.models.CardModel
import javax.inject.Inject

class DraftPresenter<V: DraftView, I: DraftInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), DraftPresenterInterface<V> {

    private val items = mutableListOf<CardModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, cardView: CardView) {
        val item = items[pos]
        getView()?.showCreateTaskDialog(item)
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

    override fun loadUserCards(id: String, recyclerAdapter: DraftAdapter) {
        interactor.loadCards(id, recyclerAdapter)
    }

    override fun observeDataChange(recyclerAdapter: DraftAdapter) = interactor.observeCards(recyclerAdapter)

    override fun changeUserCard(id: String, cardId: String, title: String, descr: String, date: String, currentTime: Long, cost: Int, active: Boolean, agreement: Boolean) {
        interactor.changeCard(id, cardId, title, descr, date, currentTime, cost, active, agreement)
    }
}