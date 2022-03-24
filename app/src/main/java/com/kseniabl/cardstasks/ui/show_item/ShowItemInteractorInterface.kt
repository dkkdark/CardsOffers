package com.kseniabl.cardstasks.ui.show_item

import com.kseniabl.cardtasks.ui.base.BaseInteractor
import com.kseniabl.cardstasks.ui.models.UserModel
import io.reactivex.rxjava3.core.Observable

interface ShowItemInteractorInterface: BaseInteractor {
    fun loadFreelancerFromCard(id: String): Observable<UserModel>
}