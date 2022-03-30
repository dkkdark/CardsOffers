package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.ui.models.AdditionalInfo
import com.kseniabl.cardstasks.ui.models.Profession
import java.io.Serializable

data class FreelancerModel (
    val id: String,
    var username: String,
    var rating: Float,
    var isFreelancer: Boolean,
    var additionalInfo: AdditionalInfo,
    var profession: Profession
) : Serializable