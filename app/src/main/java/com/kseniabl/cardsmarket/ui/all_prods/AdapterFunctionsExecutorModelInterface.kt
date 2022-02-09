package com.kseniabl.cardsmarket.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.ItemViewExecutorModel

interface AdapterFunctionsExecutorModelInterface {
    val itemCount: Int
    fun onItemClicked(pos: Int, image: CardView)
    fun onBindItemView(itemViewExecutorModel: ItemViewExecutorModel, pos: Int)

    fun addElementsToList(list: List<ExecutorModel>)
    fun addElementToList(el: ExecutorModel)
    fun getAllElements(): MutableList<ExecutorModel>
}