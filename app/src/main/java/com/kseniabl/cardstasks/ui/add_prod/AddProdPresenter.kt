package com.kseniabl.cardstasks.ui.add_prod

import android.util.Log
import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.db.RepositoryInterface
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.ItemViewCardModel
import com.kseniabl.cardstasks.ui.base.UsersCards
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.core.FlowableSubscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class AddProdPresenter<V: AddProdView, I: AddProdInteractorInterface> @Inject constructor(var interactor: I, val repository: RepositoryInterface): BasePresenter<V>(),
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

    override fun removeAllElements(list: List<CardModel>) {
        items.removeAll(list)
    }

    override fun loadUserCards(id: String, recyclerAdapter: AddProdsAdapter) {
        interactor.loadCards(id, recyclerAdapter)
    }

    override fun listToServer(list: List<CardModel>) {
        interactor.updateListInServer(list)
    }

    override fun observeDataChange(recyclerAdapter: AddProdsAdapter) = interactor.observeCards(recyclerAdapter)

    override fun changeUserCard(id: String, cardId: String, title: String, descr: String, date: String, currentTime: Long, cost: Int, active: Boolean, agreement: Boolean) {
        val card = CardModel(cardId, title, descr, date, currentTime, cost, active, agreement, id)
        repository.changeAddProdCard(card)
    }

    override fun deleteCard(userId: String, cardId: String) {
        repository.deleteAddProdCard(cardId)
    }
}