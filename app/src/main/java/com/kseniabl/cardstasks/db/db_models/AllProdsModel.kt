package com.kseniabl.cardstasks.db.db_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kseniabl.cardstasks.ui.models.CardModel

@Entity
data class AllProdsModel (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    val cards: MutableList<CardModel> = arrayListOf()
)