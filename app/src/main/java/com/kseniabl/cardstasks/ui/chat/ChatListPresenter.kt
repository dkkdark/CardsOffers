package com.kseniabl.cardstasks.ui.chat

import androidx.cardview.widget.CardView
import com.kseniabl.cardtasks.ui.base.BasePresenter
import com.kseniabl.cardtasks.ui.chat.ChatListView
import com.kseniabl.cardtasks.ui.models.CardModel
import javax.inject.Inject

class ChatListPresenter<V: ChatListView, I: ChatListInteractorInterface> @Inject constructor(val interactor: I): BasePresenter<V>(), ChatListPresenterInterface<V> {

    private val items = mutableListOf<ChatWithModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int) {
        val item = items[pos]
    }

    override fun onBindItemView(itemViewCardModel: ItemViewChatWithModel, pos: Int) {
        itemViewCardModel.bindItem(items[pos])
    }

    override fun addElementsToList(list: List<ChatWithModel>) {
        items.addAll(list)
    }

    override fun addElementToList(el: ChatWithModel, pos: Int) {
        items.add(pos, el)
    }

    override fun getAllElements(): MutableList<ChatWithModel> {
        return items
    }

    override fun removeElementFromList(el: ChatWithModel) {
        items.remove(el)
    }

    override fun getPos(el: ChatWithModel): Int {
        return items.indexOf(el)
    }

}