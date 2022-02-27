package com.kseniabl.cardsmarket.ui.add_prod

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.CurrentUser
import com.kseniabl.cardsmarket.ui.base.RetrofitApiHolder
import com.kseniabl.cardsmarket.ui.base.UserCardInteractor
import com.kseniabl.cardsmarket.ui.base.UsersCards
import com.kseniabl.cardsmarket.ui.models.CardModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AddProdInteractor @Inject constructor(): AddProdInteractorInterface, UserCardInteractor() {

    override fun observeCards(recyclerAdapter: AddProdsAdapter) {
        observeAddCards().subscribe {
            if (CurrentUser.getUser()?.id != null) {
                loadCards(CurrentUser.getUser()!!.id, recyclerAdapter)
            }
        }
    }

    override fun loadCards(id: String, recyclerAdapter: AddProdsAdapter) {
        loadAddedCards(id).subscribe { data ->
            if (data != null) {
                for (card in data) {
                        addCardsToAddProdsRecycler(card, recyclerAdapter)

                    if (!UsersCards.getAllCards().contains(card))
                        UsersCards.addCard(card)
                }
            }
        }
    }

    private fun addCardsToAddProdsRecycler(card: CardModel, addProdsAdapter: AddProdsAdapter) {
        val elements = addProdsAdapter.getElements()
        var addOrNot = true
        for (itm in elements) {
            addOrNot = itm.id != card.id
        }

        if (addOrNot && !elements.contains(card) && card.active) {
            addProdsAdapter.addElement(card)
        }
    }
}