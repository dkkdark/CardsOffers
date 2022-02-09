package com.kseniabl.cardsmarket.ui.all_prods

data class CardModel(
     val id: String,
     val title: String,
     val description: String,
     val active: Boolean,
     val date: String,
     val cost: String,
     val isAgreement: Boolean,
     val time: Long
)