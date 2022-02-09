package com.kseniabl.cardsmarket.ui.create_prod

import android.graphics.Bitmap
import com.kseniabl.cardsmarket.ui.base.PresenterInterface
import java.io.InputStream

interface CreateProdPresenterInterface<V: CreateProdView>: PresenterInterface<V> {
    fun checkUserFromDatabase(name: String, description: String, imageBoolean: Boolean)
    fun onChooseImageButtonClick()
}