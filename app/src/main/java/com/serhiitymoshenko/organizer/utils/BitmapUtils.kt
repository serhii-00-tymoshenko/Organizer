package com.serhiitymoshenko.organizer.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


fun Bitmap.resize(sizePx: Int): Bitmap {
    val bitmap = Bitmap.createScaledBitmap(this, sizePx, sizePx, false)
    return bitmap
}

fun Bitmap.toByteArray(): ByteArray? {
    if (this == null) {
        return null
    }

    val outputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    return outputStream.toByteArray()
}

fun ByteArray.tooBitmap(): Bitmap? {
    if (this == null) {
        return null
    }

    return BitmapFactory.decodeByteArray(this, 0, this.size)
}