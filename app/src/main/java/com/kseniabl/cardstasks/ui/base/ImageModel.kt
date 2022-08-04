package com.kseniabl.cardstasks.ui.base

import java.io.Serializable

data class ImageModel (
    val id: Int,
    val img: String,
    val name: String,
    val mimeType: String
): Serializable