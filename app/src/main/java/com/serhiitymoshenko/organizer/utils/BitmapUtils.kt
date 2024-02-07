package com.serhiitymoshenko.organizer.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


fun Bitmap.resize(): Bitmap {
    val bitmap = Bitmap.createScaledBitmap(this, 180, 180, false)
    return bitmap
}