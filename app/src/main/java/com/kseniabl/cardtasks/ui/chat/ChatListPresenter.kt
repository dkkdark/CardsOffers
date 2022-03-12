package com.kseniabl.cardtasks.ui.chat

import com.kseniabl.cardtasks.ui.base.BasePresenter
import javax.inject.Inject

class ChatListPresenter<V: ChatListView> @Inject constructor(): BasePresenter<V>(), ChatListPresenterInterface<V> {
}