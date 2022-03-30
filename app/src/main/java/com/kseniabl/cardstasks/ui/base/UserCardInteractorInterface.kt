package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardtasks.ui.models.CardModel
import io.reactivex.rxjava3.core.Observable

interface UserCardInteractorInterface {
    fun observeChangeCards(): Observable<CardModel>
    fun observeAddCards(): Observable<CardModel>
    fun loadAddedCards(id: String): Observable<List<CardModel>>
    fun sendTokenToServer(token: String, id: String)
    fun replaceToken(oldToken: String, newToken: String, id: String)
}