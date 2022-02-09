package com.kseniabl.cardsmarket.ui.all_prods

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.ItemViewCardModel
import javax.inject.Inject
import android.widget.ImageView
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardsmarket.ui.base.ItemViewExecutorModel


class ExecutorsAdapter @Inject constructor(var presenter: ExecutorPresenter<ExecutorView>, var context: Context, var fragment: ExecutorFragment): RecyclerView.Adapter<ExecutorsAdapter.ExecutorsViewCardModelHolder>() {

    fun addElements(list: List<ExecutorModel>) {
        presenter.addElementsToList(list)
        notifyDataSetChanged()
    }

    fun addElement(el: ExecutorModel) {
        presenter.addElementToList(el)
        notifyDataSetChanged()
    }

    fun getElements(): List<ExecutorModel> {
        return presenter.getAllElements()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExecutorsViewCardModelHolder {
        presenter.attachView(fragment)
        return ExecutorsViewCardModelHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_executors, parent, false))
    }

    override fun onBindViewHolder(holder: ExecutorsViewCardModelHolder, position: Int) {
        presenter.onBindItemView(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.itemCount
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        presenter.detachView()
        super.onDetachedFromRecyclerView(recyclerView)
    }

    inner class ExecutorsViewCardModelHolder(view: View): RecyclerView.ViewHolder(view), ItemViewExecutorModel {
        private val itemTxt: TextView = view.findViewById(R.id.itemExeName)
        private val itemDescr: TextView = view.findViewById(R.id.itemExeDescr)
        private val itemCardView: CardView = view.findViewById(R.id.itemExeCardView)
        private val itemImg: ImageView = view.findViewById(R.id.imageViewItemExe)
        private val itemRate: RatingStarView = view.findViewById(R.id.itemExeRating)
        init {
            view.setOnClickListener {
                presenter.onItemClicked(adapterPosition, itemCardView) }
        }

        override fun bindItem(item: ExecutorModel) {
            //ViewCompat.setTransitionName(itemView, item.id)

            itemTxt.text = item.name
            itemDescr.text = item.description
        }
    }

}