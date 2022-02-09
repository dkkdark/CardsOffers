package com.kseniabl.cardsmarket.ui.all_prods

import android.util.Log
import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.ItemViewCardModel
import javax.inject.Inject

class AllProdsPresenter<V: AllProdsView, I: AllProdsInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), AllProdsPresenterCardModelInterface<V> {

    private val items = mutableListOf<CardModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, image: CardView) {
        val item = items[pos]
        getView()?.openShowItemFragment(item, image)
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

    override fun loadAllCards() {
        val adapter = getView()?.provideAdapter()

        interactor.loadCards()?.let {
            it.addOnSuccessListener {
                for (card in it.children) {
                    for (el in card.children) {
                        if (el.key?.equals("profileInfo") == false) {
                            val newCard = CardModel(el.key.toString(), el.child("title").value.toString(), el.child("description").value.toString(),
                                el.child("active").value as Boolean, el.child("date").value.toString(), el.child("cost").value.toString(),
                                el.child("agreement").value as Boolean, el.child("createTime").value as Long)
                            val elements = adapter?.getElements()
                            if (elements?.contains(newCard) == false) {
                                adapter.addElement(newCard)
                            }
                        }
                    }
                }
            }
            it.addOnFailureListener {
                Log.e("AllProdsInteractorError", "Something went wrong: " + it.message)
            }
        }
    }
}