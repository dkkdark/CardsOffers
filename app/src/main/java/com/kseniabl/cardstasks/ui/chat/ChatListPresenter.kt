package com.kseniabl.cardstasks.ui.chat

import android.util.Log
import com.kseniabl.cardstasks.ui.base.ChatListSavingInterface
import com.kseniabl.cardstasks.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatListPresenter<V: ChatListView, I: ChatListInteractorInterface> @Inject constructor(val interactor: I, val chatListSaving: ChatListSavingInterface): BasePresenter<V>(), ChatListPresenterInterface<V> {

    private val items = mutableListOf<ChatWithModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int) {
        val item = items[pos]
        item.notSeenMessages = 0
        val list = chatListSaving.getChatList()
        list?.forEach { el ->
            if (el.id == item.id && el.card_id == item.card_id) {
                el.notSeenMessages = 0
            }
        }
        list?.let { chatListSaving.saveChatList(it) }
        getView()?.startChatScreenActivity(item.id, item.card_id, item.card_title, item.card_cost)
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

    override fun removeAll(list: List<ChatWithModel>) {
        items.removeAll(list)
    }

    override fun setChatList() {
        Log.e("qqq", "setChatList")
        val recyclerAdapter = getView()?.provideAdapter()
        val elementsInRecycler = recyclerAdapter?.getElements()
        val data = chatListSaving.getChatList()

        val listToRemove = arrayListOf<ChatWithModel>()
        if (!elementsInRecycler.isNullOrEmpty()) {
            for (el in elementsInRecycler) {
                if (data?.any { it.id == el.id } == true) {
                    listToRemove.add(el)
                }
            }
        }
        recyclerAdapter?.removeAllInList(listToRemove)

        if (!data.isNullOrEmpty()) {
            for (chat in data) {
                MainScope().launch {
                    withContext(Dispatchers.Main) { recyclerAdapter?.addElement(chat, 0) }
                }
            }
        }
    }

}