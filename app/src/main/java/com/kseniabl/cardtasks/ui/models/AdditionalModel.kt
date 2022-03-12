package com.kseniabl.cardtasks.ui.models

data class AdditionalInfo (
    var description: String,
    var country: String,
    var city: String,
    var typeOfWork: String
)

data class Profession(
    var description: String,
    var specialization: String,
    var tags: List<String>
)