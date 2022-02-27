package com.kseniabl.cardsmarket.ui.add_prod

import android.util.Log
import androidx.cardview.widget.CardView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.kseniabl.cardsmarket.ui.base.*
import com.kseniabl.cardsmarket.ui.models.CardModel
import com.kseniabl.cardsmarket.ui.models.MessageModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AddProdPresenter<V: AddProdView, I: AddProdInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), AddProdPresenterCardModelInterface<V> {

    private val items = mutableListOf<CardModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, image: CardView) {
        val item = items[pos]
        getView()?.showCreateTaskDialog()
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

    override fun loadBaseCards() {
        val recyclerAdapter = getView()?.provideAdapter()

        val cards = UsersCards.getAllCards()
        if (cards.isNotEmpty()) {
            for (card in cards) {
                if (recyclerAdapter?.getElements()?.contains(card) == false) {
                    recyclerAdapter.addElement(card)
                    //loadAddedCards()
                }
            }
        }
    }

    override fun loadUserCards(id: String, recyclerAdapter: AddProdsAdapter) {
        interactor.loadCards(id, recyclerAdapter)
        interactor.observeCards(recyclerAdapter)
    }

}