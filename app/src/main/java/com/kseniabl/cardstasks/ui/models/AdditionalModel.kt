package com.kseniabl.cardstasks.ui.models

import java.io.Serializable

data class AdditionalInfo (
    var description: String,
    var country: String,
    var city: String,
    var typeOfWork: String
) : Serializable

data class Profession(
    var description: String,
    var specialization: String,
    var tags: List<String>
) : Serializable