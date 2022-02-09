package com.kseniabl.cardsmarket.ui.create_prod

import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import com.kseniabl.cardsmarket.ui.base.BaseView
import java.io.File
import java.io.InputStream

interface CreateProdView: BaseView {
    fun returnResultLauncher(): ActivityResultLauncher<Intent>
    fun createImageFile(): File
    fun returnPackageManager(): PackageManager
    fun returnStream(): InputStream?
    fun returnPath(): String?
    fun returnRealPath(): String?
}