package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.ui.models.CardModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.processors.PublishProcessor

interface UserCardInteractorInterface {
    fun observeChangeCards(): PublishProcessor<CardModel>
    fun observeAddCards(): PublishProcessor<CardModel>
    fun loadAddedCards(id: String): Observable<List<CardModel>>
    fun sendTokenToServer(token: String, id: String)
    fun replaceToken(oldToken: String, newToken: String, id: String)
}