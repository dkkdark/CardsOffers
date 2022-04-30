package com.kseniabl.cardstasks.db.view_models

import androidx.lifecycle.*
import com.kseniabl.cardstasks.db.ChatRepository
import javax.inject.Inject

class AllCardsViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {

    val allCards = repository.getAllCards().asLiveData()
}