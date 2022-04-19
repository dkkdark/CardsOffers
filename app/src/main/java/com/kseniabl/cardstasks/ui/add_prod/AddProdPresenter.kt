package com.kseniabl.cardtasks.ui.add_prod

import android.util.Log
import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.add_prod.AddProdInteractorInterface
import com.kseniabl.cardstasks.ui.add_prod.AddProdPresenterCardModelInterface
import com.kseniabl.cardstasks.ui.add_prod.AddProdView
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.ItemViewCardModel
import com.kseniabl.cardstasks.ui.base.UsersCards
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.core.FlowableSubscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class AddProdPresenter<V: AddProdView, I: AddProdInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(),
    AddProdPresenterCardModelInterface<V> {

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

    override fun deleteCard(userId: String, cardId: String) {
        interactor.deleteCard(userId, cardId)
            .subscribe(object : FlowableSubscriber<MessageModel> {
                override fun onSubscribe(s: Subscription) {
                    s.request(Long.MAX_VALUE)
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        UsersCards.deleteCard(cardId)
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "changeCard error ${e?.message}")
                }

                override fun onComplete() {
                }

            })
    }
}