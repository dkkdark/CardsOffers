package com.kseniabl.cardstasks.ui.show_item

import com.kseniabl.cardstasks.ui.base.ImageModel
import com.kseniabl.cardtasks.ui.base.BaseInteractor
import com.kseniabl.cardstasks.ui.models.UserModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

interface ShowItemInteractorInterface: BaseInteractor {
    fun loadFreelancerFromCard(id: String): Observable<UserModel>
    fun getProfileImage(id: String): Flowable<ImageModel>
}