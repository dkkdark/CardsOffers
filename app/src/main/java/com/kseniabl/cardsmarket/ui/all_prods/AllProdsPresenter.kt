package com.kseniabl.cardsmarket.ui.all_prods

import android.util.Log
import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.ItemViewCardModel
import com.kseniabl.cardsmarket.ui.models.CardModel
import javax.inject.Inject

class AllProdsPresenter<V: AllProdsView, I: AllProdsInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), AllProdsPresenterCardModelInterface<V> {

    private val items = mutableListOf<CardModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int) {
        val item = items[pos]
        //getView()?.openShowItemFragment(item)
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
        /*val adapter = getView()?.provideAdapter()

        interactor.loadCards()?.let {
            it.addOnSuccessListener {
                for (card in it.children) {
                    for (el in card.children) {
                        if (el.key?.equals("profileInfo") == false) {
                            val newCard = CardModel(el.key.toString(), el.child("title").value.toString(), el.child("description").value.toString(),
                                el.child("active").value as Boolean, el.child("date").value.toString(), el.child("cost").value.toString(),
                                el.child("agreement").value as Boolean, el.child("createTime").value as Long)
                            val elements = adapter?.getElements()
                            var addOrNot = true
                            if (elements != null) {
                                for (itm in elements) {
                                    addOrNot = itm.id != newCard.id
                                }
                            }
                            if (addOrNot && elements?.contains(newCard) == false && newCard.active) {
                                adapter.addElement(newCard)
                            }
                        }
                    }
                }
            }
            it.addOnFailureListener {
                Log.e("AllProdsInteractorError", "Something went wrong: " + it.message)
            }
        }*/
    }
}