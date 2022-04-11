package com.kseniabl.cardstasks.ui.chat

interface AdapterFunctionsChatWithModeInterface {
    val itemCount: Int
    fun onItemClicked(pos: Int)
    fun onBindItemView(itemViewCardModel: ItemViewChatWithModel, pos: Int)

    fun addElementsToList(list: List<ChatWithModel>)
    fun addElementToList(el: ChatWithModel, pos: Int)
    fun getAllElements(): MutableList<ChatWithModel>
    fun removeElementFromList(el: ChatWithModel)
    fun getPos(el: ChatWithModel): Int
    fun removeAll(list: List<ChatWithModel>)
}