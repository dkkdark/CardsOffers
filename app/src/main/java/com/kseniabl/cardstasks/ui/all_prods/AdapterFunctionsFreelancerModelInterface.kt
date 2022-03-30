package com.kseniabl.cardstasks.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardstasks.ui.base.ItemViewFreelancerModel
import com.kseniabl.cardstasks.ui.models.UserModel

interface AdapterFunctionsFreelancerModelInterface {
    val itemCount: Int
    fun onItemClicked(pos: Int, image: CardView)
    fun onBindItemView(itemViewFreelancerModel: ItemViewFreelancerModel, pos: Int)

    fun addElementsToList(list: List<FreelancerModel>)
    fun addElementToList(el: FreelancerModel)
    fun getAllElements(): MutableList<FreelancerModel>
}