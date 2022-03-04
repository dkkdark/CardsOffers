package com.kseniabl.cardsmarket.ui.models

import java.io.Serializable

data class CardModel(
    var id: String,
    var title: String,
    var description: String,
    var date: String,
    var createTime: Long,
    var cost: Int,
    var active: Boolean,
    var agreement: Boolean,
    var user_id: String
) : Serializable