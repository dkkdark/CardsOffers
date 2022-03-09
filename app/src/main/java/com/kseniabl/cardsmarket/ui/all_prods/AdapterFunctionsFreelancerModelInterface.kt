package com.kseniabl.cardsmarket.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.ItemViewFreelancerModel

interface AdapterFunctionsFreelancerModelInterface {
    val itemCount: Int
    fun onItemClicked(pos: Int, image: CardView)
    fun onBindItemView(itemViewFreelancerModel: ItemViewFreelancerModel, pos: Int)

    fun addElementsToList(list: List<FreelancerModel>)
    fun addElementToList(el: FreelancerModel)
    fun getAllElements(): MutableList<FreelancerModel>
}