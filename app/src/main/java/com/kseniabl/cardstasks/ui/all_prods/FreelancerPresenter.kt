package com.kseniabl.cardstasks.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.ItemViewFreelancerModel
import javax.inject.Inject

class FreelancerPresenter<V: FreelancerView, I: FreelancerInteractorInterface> @Inject constructor(var interactor: I, val currentUserClass: CurrentUserClass):
    BasePresenter<V>(), FreelancerPresenterCardModelInterface<V> {

    private val items = mutableListOf<FreelancerModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, image: CardView) {
        val item = items[pos]
        getView()?.loadFreelancerDetails(item)
    }

    override fun onBindItemView(itemViewFreelancerModel: ItemViewFreelancerModel, pos: Int) {
        itemViewFreelancerModel.bindItem(items[pos])
    }

    override fun addElementsToList(list: List<FreelancerModel>) {
        items.addAll(list)
    }

    override fun addElementToList(el: FreelancerModel) {
        items.add(el)
    }

    override fun getAllElements(): MutableList<FreelancerModel> {
        return items
    }

    override fun loadFreelancers() {
        val adapter = getView()?.provideAdapter()
        interactor.loadFreelancers().subscribe { data ->
            for (freelancer in data) {
                if (freelancer.id != currentUserClass.readSharedPref()?.id)
                    adapter?.addElement(freelancer)
            }
        }
    }

}