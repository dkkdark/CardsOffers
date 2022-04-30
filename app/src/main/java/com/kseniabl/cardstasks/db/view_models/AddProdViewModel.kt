package com.kseniabl.cardstasks.db.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kseniabl.cardstasks.db.ChatRepository
import javax.inject.Inject

class AddProdViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {
    val allCards = repository.getAddProdCards()
}