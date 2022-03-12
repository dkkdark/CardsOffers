package com.kseniabl.cardtasks.ui.all_prods

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.base.ItemViewCardModel
import javax.inject.Inject
import com.kseniabl.cardtasks.ui.models.CardModel
import java.util.*


class AllProdsAdapter @Inject constructor(var presenter: AllProdsPresenter<AllProdsView, AllProdsInteractorInterface>, var context: Context, var fragment: AllProdsFragment): RecyclerView.Adapter<AllProdsAdapter.AllProdsHolder>() {

    fun addElements(list: List<CardModel>) {
        presenter.addElementsToList(list)
        notifyDataSetChanged()
    }

    fun addElement(el: CardModel, pos: Int) {
        presenter.addElementToList(el, pos)
        notifyDataSetChanged()
    }

    fun getElements(): List<CardModel> {
        return presenter.getAllElements()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProdsHolder {
        presenter.attachView(fragment)
        return AllProdsHolder(LayoutInflater.from(parent.context).inflate(R.layout.all_prods_item, parent, false))
    }

    override fun onBindViewHolder(holder: AllProdsHolder, position: Int) {
        presenter.onBindItemView(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.itemCount
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        presenter.detachView()
        super.onDetachedFromRecyclerView(recyclerView)
    }

    inner class AllProdsHolder(view: View): RecyclerView.ViewHolder(view), ItemViewCardModel {
        private val cardTxt: TextView = view.findViewById(R.id.cardText)
        private val cardDescr: TextView = view.findViewById(R.id.cardDescr)
        private val cardTime: TextView = view.findViewById(R.id.cardTime)
        private val cardDate: TextView = view.findViewById(R.id.cardDate)
        private val cardCost: TextView = view.findViewById(R.id.cardCost)
        private var cardView: CardView = view.findViewById(R.id.prodItemCardView)
        init {
            view.setOnClickListener {
                presenter.onItemClicked(adapterPosition, cardView)
            }
        }

        override fun bindItem(item: CardModel) {
            ViewCompat.setTransitionName(cardView, item.id)

            cardTxt.text = item.title
            cardDescr.text = item.description
            cardDate.text = item.date
            if (item.agreement)
                cardCost.text = "By agreement"
            else
                cardCost.text = "${item.cost} $"

            val currentTime = Calendar.getInstance().time.time
            val distinction = currentTime - item.createTime
            val numOfDays = (distinction / (1000 * 60 * 60 * 24)).toInt()
            val hours = (distinction / (1000 * 60 * 60)).toInt()
            val minutes = (distinction / (1000 * 60)).toInt()
            if (numOfDays == 0 && hours == 0 && minutes == 0)
                cardTime.text = "seconds ago"
            else if (numOfDays == 0 && hours == 0 && minutes != 0)
                cardTime.text = "$minutes minutes ago"
            else if (numOfDays == 0 && hours != 0 && minutes != 0)
                cardTime.text = "$hours hours ago"
            else if (numOfDays != 0 && hours != 0 && minutes != 0)
                cardTime.text = "$numOfDays days ago"
        }
    }
}