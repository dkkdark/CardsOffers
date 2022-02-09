package com.kseniabl.cardsmarket.ui.base

import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.all_prods.CardModel

interface AdapterFunctionsCardModelInterface {
    val itemCount: Int
    fun onItemClicked(pos: Int, image: CardView)
    fun onBindItemView(itemViewCardModel: ItemViewCardModel, pos: Int)

    fun addElementsToList(list: List<CardModel>)
    fun addElementToList(el: CardModel)
    fun getAllElements(): MutableList<CardModel>
}