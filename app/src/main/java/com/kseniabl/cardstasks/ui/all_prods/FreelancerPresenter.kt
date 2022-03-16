package com.kseniabl.cardtasks.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.all_prods.FreelancerView
import com.kseniabl.cardtasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.ItemViewFreelancerModel
import com.kseniabl.cardstasks.ui.models.UserModel
import javax.inject.Inject

class FreelancerPresenter<V: FreelancerView, I: FreelancerInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), FreelancerPresenterCardModelInterface<V> {

    private val items = mutableListOf<UserModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, image: CardView) {
        val item = items[pos]
        getView()?.loadFreelancerDetails(item)
    }

    override fun onBindItemView(itemViewFreelancerModel: ItemViewFreelancerModel, pos: Int) {
        itemViewFreelancerModel.bindItem(items[pos])
    }

    override fun addElementsToList(list: List<UserModel>) {
        items.addAll(list)
    }

    override fun addElementToList(el: UserModel) {
        items.add(el)
    }

    override fun getAllElements(): MutableList<UserModel> {
        return items
    }

    override fun loadFreelancers() {
        val adapter = getView()?.provideAdapter()
        interactor.loadFreelancers().subscribe { data ->
            for (freelancer in data) {
                adapter?.addElement(freelancer)
            }
        }
    }

}