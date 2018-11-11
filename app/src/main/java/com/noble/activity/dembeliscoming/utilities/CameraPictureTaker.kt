package com.noble.activity.dembeliscoming.utilities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraPictureTaker(private val fragment: Fragment) {
    val TAKE_PICTURE_REQUEST_CODE = 1

    private val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
        .format(Date())

    var imageUri: Uri? = null

    fun takeCameraPicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(fragment.activity?.packageManager) != null) {
            val imageFile = createImageFile()
            imageUri = FileProvider.getUriForFile(
                fragment.activity!!,
                "com.noble.activity.dembeliscoming.fileprovider",
                imageFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            fragment.startActivityForResult(intent, TAKE_PICTURE_REQUEST_CODE)
        }
    }

    private fun createImageFile(): File {
        val storageDir: File = fragment.activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${simpleDateFormat.format(Date())}_",
            ".jpg",
            storageDir
        )
    }
}