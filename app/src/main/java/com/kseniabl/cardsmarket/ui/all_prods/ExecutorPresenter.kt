package com.kseniabl.cardsmarket.ui.all_prods

import android.util.Log
import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.ItemViewCardModel
import com.kseniabl.cardsmarket.ui.base.ItemViewExecutorModel
import javax.inject.Inject

class ExecutorPresenter<V: ExecutorView, I: ExecutorInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), ExecutorPresenterCardModelInterface<V> {

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

    override fun loadExecutors() {
        val adapter = getView()?.provideAdapter()

        interactor.loadCards().addOnSuccessListener {
            for (el in it.children) {
                if (el.child("profileInfo").child("isExecutor").value == true) {
                    val executorModel = ExecutorModel(el.child("profileInfo").child("name").value.toString(), el.child("profileInfo").child("profession").child("specialization").value.toString())
                    adapter?.addElement(executorModel)
                }
            }
        }
    }

}