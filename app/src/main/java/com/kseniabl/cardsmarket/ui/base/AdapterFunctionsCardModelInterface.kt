package com.kseniabl.cardsmarket.ui.base

import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.models.CardModel

interface AdapterFunctionsCardModelInterface {
    val itemCount: Int
    fun onItemClicked(pos: Int)
    fun onBindItemView(itemViewCardModel: ItemViewCardModel, pos: Int)

    fun addElementsToList(list: List<CardModel>)
    fun addElementToList(el: CardModel, pos: Int)
    fun getAllElements(): MutableList<CardModel>
    fun removeElementFromList(el: CardModel)
    fun getPos(el: CardModel): Int
}