package com.kseniabl.cardsmarket.ui.add_prod

import android.util.Log
import androidx.cardview.widget.CardView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.kseniabl.cardsmarket.ui.all_prods.CardModel
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.ItemViewCardModel
import com.kseniabl.cardsmarket.ui.base.UsersCards
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

    override fun loadAddedCards() {
        val recyclerAdapter = getView()?.provideAdapter()

        interactor.getChilds()?.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (el in snapshot.children) {
                        if (el.key?.equals("profileInfo") == false && el.child("title").exists() && el.child("description").exists() && el.child("active").exists()
                            && el.child("date").exists() && el.child("cost").exists() && el.child("agreement").exists() && el.child("createTime").exists()) {

                            val newCard = CardModel(el.key.toString(), el.child("title").value.toString(), el.child("description").value.toString(),
                                el.child("active").value as Boolean, el.child("date").value.toString(),
                                el.child("cost").value.toString(), el.child("agreement").value as Boolean, el.child("createTime").value as Long)
                            val elements = recyclerAdapter?.getElements()
                            var addOrNot = true
                            if (elements != null) {
                                for (itm in elements) {
                                    addOrNot = itm.id != newCard.id
                                }
                            }

                            if (addOrNot && elements?.contains(newCard) == false && !newCard.active) {
                                recyclerAdapter.addElement(newCard)
                                UsersCards.addCard(newCard)
                                //getView()?.startTransition()
                            }

                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("AddProdPresenterError", "Error: $error")
                }
            }
        )
    }
}