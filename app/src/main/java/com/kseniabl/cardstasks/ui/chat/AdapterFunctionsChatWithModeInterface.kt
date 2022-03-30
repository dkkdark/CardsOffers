package com.kseniabl.cardstasks.ui.chat

import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.base.ItemViewCardModel
import com.kseniabl.cardtasks.ui.models.CardModel

interface AdapterFunctionsChatWithModeInterface {
    val itemCount: Int
    fun onItemClicked(pos: Int)
    fun onBindItemView(itemViewCardModel: ItemViewChatWithModel, pos: Int)

    fun addElementsToList(list: List<ChatWithModel>)
    fun addElementToList(el: ChatWithModel, pos: Int)
    fun getAllElements(): MutableList<ChatWithModel>
    fun removeElementFromList(el: ChatWithModel)
    fun getPos(el: ChatWithModel): Int
}