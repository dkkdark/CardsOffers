package com.kseniabl.cardsmarket.ui.create_prod

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import com.kseniabl.cardsmarket.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_create_prod.*
import javax.inject.Inject

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.kseniabl.cardsmarket.R
import android.content.DialogInterface

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.io.*
import androidx.core.content.FileProvider
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

import com.squareup.picasso.Picasso
import com.kseniabl.cardsmarket.ui.base.GetRealPath

import android.graphics.BitmapFactory
import androidx.activity.result.ActivityResultLauncher


class CreateProdActivity: CreateProdView, BaseActivity() {

    @Inject
    lateinit var presenter: CreateProdPresenterInterface<CreateProdView>

    private var realPath: String? = ""
    private var imgFile: File? = null
    private var galleryOrCamera = false
    private var path: String? = null
    private var stream: InputStream? = null
    private var isProgressShow = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_prod)
        presenter.attachView(this)

        createProdImage.setOnClickListener { checkAndRequestPermissions() }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showLoadProgress() {
        isProgressShow = true
    }

    override fun hideLoadProgress() {
        isProgressShow = false
    }

    override fun onBackPressed() {
        checkUser()
        super.onBackPressed()
    }

    private fun checkUser() {
        presenter.checkUserFromDatabase(createProdText.text.toString(), createProdDescr.text.toString(), galleryOrCamera)
    }

    @Throws(IOException::class)
    override fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
            .apply {
            realPath = absolutePath
            imgFile = filesDir
        }
    }

    override fun returnResultLauncher(): ActivityResultLauncher<Intent> = resultLauncher

    override fun returnPackageManager(): PackageManager = packageManager

    override fun returnStream(): InputStream? = stream
    override fun returnPath(): String? = path
    override fun returnRealPath(): String? = realPath

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data?.data == null) {
                if (realPath != null && realPath?.isNotEmpty() == true) {
                    Picasso.with(this).load("file:$realPath").into(createProdImage)
                    galleryOrCamera = false
                }
            }
            else {
                val uri: Uri = result.data?.data!!
                try {
                    path = GetRealPath.getRealPath(this, uri)
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(this, "Что-то пошло не так. Попробуйте загрузить фото из галереи.", Toast.LENGTH_SHORT).show()
                }
                if (path != null) {
                    Picasso.with(this).load("file:$path").into(createProdImage)

                    stream = result.data?.data?.let { contentResolver.openInputStream(it) }
                    galleryOrCamera = true
                }
            }
        }
    }

    val REQUEST_ID_MULTIPLE_PERMISSIONS = 7

    private fun checkAndRequestPermissions(): Boolean {
        val camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val wtite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)

        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (wtite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), REQUEST_ID_MULTIPLE_PERMISSIONS)
            return false
        }
        else
            presenter.onChooseImageButtonClick()
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_ID_MULTIPLE_PERMISSIONS -> {
                val perms: MutableMap<String, Int> = HashMap()
                perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.CAMERA] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.READ_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED

                if (grantResults.isNotEmpty()) {
                    var i = 0
                    while (i < permissions.size) {
                        perms[permissions[i]] = grantResults[i]
                        i++
                    }

                    if (perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED && perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED && perms[Manifest.permission.READ_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED) {
                        presenter.onChooseImageButtonClick()
                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.CAMERA
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                        ) {
                            showDialogOK("Camera and Storage Permission required for this app",
                                DialogInterface.OnClickListener { dialog, which ->
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> checkAndRequestPermissions()
                                        DialogInterface.BUTTON_NEGATIVE -> {}
                                    }
                                })
                        } else {
                            Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun showDialogOK(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", okListener)
            .create()
            .show()
    }

}