package com.kseniabl.cardsmarket.ui.add_prod

import android.util.Log
import androidx.cardview.widget.CardView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.ItemViewCardModel
import com.kseniabl.cardsmarket.ui.base.UsersCards
import com.kseniabl.cardsmarket.ui.models.CardModel
import javax.inject.Inject

class DraftPresenter<V: DraftView, I: DraftInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), DraftPresenterInterface<V> {

    private val items = mutableListOf<CardModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, image: CardView) {
        val item = items[pos]
    }

    override fun onBindItemView(itemViewCardModel: ItemViewCardModel, pos: Int) {
        itemViewCardModel.bindItem(items[pos])
    }

    override fun addElementsToList(list: List<CardModel>) {
        items.addAll(list)
    }

    override fun addElementToList(el: CardModel) {
        items.add(el)
    }

    override fun getAllElements(): MutableList<CardModel> {
        return items
    }

    override fun loadUserCards(id: String, recyclerAdapter: DraftAdapter) {
        interactor.loadCards(id, recyclerAdapter)
        interactor.observeCards(recyclerAdapter)
    }
}