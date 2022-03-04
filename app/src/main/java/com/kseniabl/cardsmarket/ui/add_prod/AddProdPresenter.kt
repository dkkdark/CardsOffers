package com.kseniabl.cardsmarket.ui.add_prod

import android.widget.TextView
import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.*
import com.kseniabl.cardsmarket.ui.models.CardModel
import javax.inject.Inject

class AddProdPresenter<V: AddProdView, I: AddProdInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), AddProdPresenterCardModelInterface<V> {

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

    override fun loadBaseCards() {
        val recyclerAdapter = getView()?.provideAdapter()

        val cards = UsersCards.getAllCards()
        if (cards.isNotEmpty()) {
            for (card in cards) {
                if (recyclerAdapter?.getElements()?.contains(card) == false) {
                    recyclerAdapter.addElement(card, 0)
                    //loadAddedCards()
                }
            }
        }
    }

    override fun loadUserCards(id: String, recyclerAdapter: AddProdsAdapter) {
        interactor.loadCards(id, recyclerAdapter)
    }

    override fun observeDataChange(recyclerAdapter: AddProdsAdapter) = interactor.observeCards(recyclerAdapter)

    override fun changeUserCard(id: String, cardId: String, title: String, descr: String, date: String, currentTime: Long, cost: Int, active: Boolean, agreement: Boolean) {
        interactor.changeCard(id, cardId, title, descr, date, currentTime, cost, active, agreement)
    }
}