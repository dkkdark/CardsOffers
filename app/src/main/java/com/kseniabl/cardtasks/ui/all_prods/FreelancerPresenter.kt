package com.kseniabl.cardtasks.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardtasks.ui.base.BasePresenter
import com.kseniabl.cardtasks.ui.base.ItemViewFreelancerModel
import javax.inject.Inject

class FreelancerPresenter<V: FreelancerView, I: FreelancerInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), FreelancerPresenterCardModelInterface<V> {

    private val items = mutableListOf<FreelancerModel>()

    override val itemCount: Int
        get() = items.size

    override fun onItemClicked(pos: Int, image: CardView) {
        val item = items[pos]
        getView()?.loadFreelancerDetails()
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
            for (Freelancer in data) {
                val FreelancerModel = FreelancerModel(Freelancer.username, Freelancer.profession.specialization)
                adapter?.addElement(FreelancerModel)
            }
        }

        /*interactor.loadFreelancers().addOnSuccessListener {
            for (el in it.children) {
                if (el.child("profileInfo").child("isFreelancer").value == true) {
                    val FreelancerModel = FreelancerModel(el.child("profileInfo").child("name").value.toString(), el.child("profileInfo").child("profession").child("specialization").value.toString())
                    adapter?.addElement(FreelancerModel)
                }
            }
        }*/
    }

}