package com.kseniabl.cardstasks.db

import androidx.lifecycle.LiveData
import com.kseniabl.cardstasks.ui.models.CardModel
import io.reactivex.rxjava3.core.Single

interface RepositoryInterface {
    fun insertAddProdsCardsList(list: List<CardModel>)
    fun insertAddProdCard(card: CardModel)
    fun getAddProdCards(): LiveData<List<CardModel>>
    fun deleteAddProdCard(card: CardModel)
    fun deleteAddProdCardsList()
}