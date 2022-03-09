package com.kseniabl.cardsmarket.ui.show_item

import com.kseniabl.cardsmarket.ui.base.BaseInteractor
import com.kseniabl.cardsmarket.ui.models.UserModel
import io.reactivex.rxjava3.core.Observable

interface ShowItemInteractorInterface: BaseInteractor {
    fun loadFreelancerFromCard(id: String): Observable<UserModel>
}