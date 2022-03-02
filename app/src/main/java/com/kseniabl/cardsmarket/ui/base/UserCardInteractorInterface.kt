package com.kseniabl.cardsmarket.ui.base

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.models.CardModel
import io.reactivex.rxjava3.core.Observable

interface UserCardInteractorInterface {
    fun observeChangeCards(): Observable<CardModel>
    fun observeAddCards(): Observable<CardModel>
    fun loadAddedCards(id: String): Observable<List<CardModel>>
}