package com.kseniabl.cardstasks.db

import androidx.lifecycle.LiveData
import com.kseniabl.cardstasks.db.db_models.AllProdsModel
import com.kseniabl.cardstasks.ui.models.CardModel
import io.reactivex.rxjava3.core.Single

interface RepositoryInterface {
    fun insertAddProdsCardsList(list: List<CardModel>)
    fun insertAddProdCard(card: CardModel)
    fun getAddProdCards(): LiveData<List<CardModel>>
    fun allAddProdCards(): List<CardModel>
    fun deleteAddProdCard(card_id: String)
    fun deleteAddProdCardsList()
    fun allCardsAll(): List<AllProdsModel>

    fun changeAddProdCard(card: CardModel)
}