package com.kseniabl.cardsmarket.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.ItemViewCardModel
import com.kseniabl.cardsmarket.ui.base.ItemViewExecutorModel
import javax.inject.Inject

class ExecutorPresenter<V: ExecutorView> @Inject constructor(): BasePresenter<V>(), ExecutorPresenterCardModelInterface<V> {

    private val items = mutableListOf<ExecutorModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, image: CardView) {
        val item = items[pos]
    }

    override fun onBindItemView(itemViewExecutorModel: ItemViewExecutorModel, pos: Int) {
        itemViewExecutorModel.bindItem(items[pos])
    }

    override fun addElementsToList(list: List<ExecutorModel>) {
        items.addAll(list)
    }

    override fun addElementToList(el: ExecutorModel) {
        items.add(el)
    }

    override fun getAllElements(): MutableList<ExecutorModel> {
        return items
    }
}