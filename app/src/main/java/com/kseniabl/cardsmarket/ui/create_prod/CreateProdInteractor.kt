package com.kseniabl.cardsmarket.ui.create_prod

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class CreateProdInteractor @Inject constructor(var database: DatabaseReference, var auth: FirebaseAuth): CreateProdInteractorInterface {

    override fun addNewCard(name: String, description: String, image: String) {
        val id = getUserId()

        id?.let {
            val key = database.child("users").child(it).push()
            key.child("name").setValue(name)
            key.child("description").setValue(description)
            key.child("image").setValue(image)
        }

    }

    override fun uploadToStorage(bitmap: Bitmap, imgName: String): UploadTask {
        val userId = getUserId()

        val path = "images/$userId/$imgName"
        val baos = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val data = baos.toByteArray()
        val storage = FirebaseStorage.getInstance()
        val reference = storage.reference.child(path)
        val uploadTask = reference.putBytes(data)
        return uploadTask
    }

    override fun getUserId(): String? {
        val user = auth.currentUser
        var id: String? = null
        user?.let { id = user.uid }
        return id
    }
}