package com.kseniabl.cardsmarket.ui.create_prod

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import com.google.firebase.storage.FirebaseStorage
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.all_prods.CardModel
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.base.UsersCards
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CreateProdPresenter<V: CreateProdView, I: CreateProdInteractorInterface> @Inject constructor(var interactor: I, var context: Context): BasePresenter<V>(), CreateProdPresenterInterface<V> {

    override fun checkUserFromDatabase(name: String, description: String, imageBoolean: Boolean) {
        cardName = name
        cardDescription = description
        if (imageBoolean)
            uploadImageFromGallery()
        else
            uploadImageFromCamera()
    }

    var photoURI: Uri? = null
    var cardName: String = ""
    var cardDescription: String = ""

    override fun onChooseImageButtonClick() {
        val pickIntent = Intent()
        pickIntent.type = "image/*"
        pickIntent.action = Intent.ACTION_GET_CONTENT

        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            getView()?.returnPackageManager()?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    val photoFile: File? = try {
                        getView()?.createImageFile()
                    } catch (ex: IOException) {
                        null
                    }
                    photoFile?.also {
                        photoURI = FileProvider.getUriForFile(context, "com.example.android.fileprovider", it)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    }
                }
            }
        }

        val pickTitle = "Выбрать фото"
        val chooserIntent = Intent.createChooser(pickIntent, pickTitle)
        chooserIntent.putExtra(
            Intent.EXTRA_INITIAL_INTENTS, arrayOf(takePhotoIntent)
        )
        getView()?.returnResultLauncher()?.launch(chooserIntent)
    }

    private fun getImageReference(bitmap: Bitmap, filename: String) {
        interactor.uploadToStorage(bitmap, filename).addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                val image = it.toString()
                interactor.addNewCard(cardName, cardDescription, image)
            }
        }
    }

    private fun addNewCardWithoutImage() {
        interactor.addNewCard(cardName, cardDescription, "")
    }

    private fun uploadImageFromCamera() {
        val realPath = getView()?.returnRealPath()

        if (realPath?.isEmpty() == true || realPath == null)
            addNewCardWithoutImage()
        else {
            val bitmap = BitmapFactory.decodeFile(realPath)
            val filename = realPath.substring(realPath.lastIndexOf("/").plus(1))
            getImageReference(bitmap, filename)
        }
    }

    private fun uploadImageFromGallery() {
        val stream = getView()?.returnStream()
        val path = getView()?.returnPath()

        if (path == null || stream == null)
            addNewCardWithoutImage()
        else {
            val bitmap = BitmapFactory.decodeStream(stream)
            val filename = path.substring(path.lastIndexOf("/").plus(1))
            getImageReference(bitmap, filename)
        }
    }

}