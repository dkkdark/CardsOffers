package com.kseniabl.cardsmarket.ui.create_prod

import android.graphics.Bitmap
import com.google.firebase.storage.UploadTask
import com.kseniabl.cardsmarket.ui.base.BaseInteractor

interface CreateProdInteractorInterface: BaseInteractor {
    fun addNewCard(name: String, description: String, image: String)
    fun getUserId(): String?
    fun uploadToStorage(bitmap: Bitmap, imgName: String): UploadTask
}