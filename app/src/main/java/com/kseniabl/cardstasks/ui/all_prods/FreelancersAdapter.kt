package com.kseniabl.cardtasks.ui.all_prods

import android.content.Context
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kseniabl.cardtasks.R
import javax.inject.Inject
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardstasks.ui.all_prods.FreelancerFragment
import com.kseniabl.cardstasks.ui.all_prods.FreelancerInteractorInterface
import com.kseniabl.cardstasks.ui.all_prods.FreelancerPresenter
import com.kseniabl.cardstasks.ui.all_prods.FreelancerView
import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardstasks.ui.base.ItemViewFreelancerModel


class FreelancersAdapter @Inject constructor(var presenter: FreelancerPresenter<FreelancerView, FreelancerInteractorInterface>, var context: Context, var fragment: FreelancerFragment): RecyclerView.Adapter<FreelancersAdapter.FreelancersViewCardModelHolder>() {

    fun addElements(list: List<FreelancerModel>) {
        presenter.addElementsToList(list)
        notifyDataSetChanged()
    }

    fun addElement(el: FreelancerModel) {
        presenter.addElementToList(el)
        notifyDataSetChanged()
    }

    fun getElements(): List<FreelancerModel> {
        return presenter.getAllElements()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreelancersViewCardModelHolder {
        presenter.attachView(fragment)
        return FreelancersViewCardModelHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_freelancer, parent, false))
    }

    override fun onBindViewHolder(holder: FreelancersViewCardModelHolder, position: Int) {
        presenter.onBindItemView(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.itemCount
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        presenter.detachView()
        super.onDetachedFromRecyclerView(recyclerView)
    }

    inner class FreelancersViewCardModelHolder(view: View): RecyclerView.ViewHolder(view),
        ItemViewFreelancerModel {
        private val itemTxt: TextView = view.findViewById(R.id.itemExeName)
        private val itemDescr: TextView = view.findViewById(R.id.itemExeDescr)
        private val itemCardView: CardView = view.findViewById(R.id.itemExeCardView)
        private val itemImg: ImageView = view.findViewById(R.id.imageViewItemExe)
        private val itemRate: RatingStarView = view.findViewById(R.id.itemExeRating)
        init {
            view.setOnClickListener {
                presenter.onItemClicked(adapterPosition, itemCardView) }
        }

        override fun bindItem(item: FreelancerModel) {
            //ViewCompat.setTransitionName(itemView, item.id)

            itemTxt.text = item.username
            itemDescr.text = item.profession.description
            val bytes = Base64.decode(item.img?.img, Base64.DEFAULT)
            Glide.with(context).load(bytes).placeholder(R.drawable.user).into(itemImg)
        }
    }

}