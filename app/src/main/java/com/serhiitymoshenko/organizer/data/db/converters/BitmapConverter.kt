package com.serhiitymoshenko.organizer.data.db.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

@TypeConverters
class BitmapConverter {
    @TypeConverter
    fun bitmapToBase64(bitmap: Bitmap?): ByteArray? {
        if (bitmap == null) {
            return null
        }

        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun base64ToBitmap(byteArray: ByteArray?): Bitmap? {
        if (byteArray == null) {
            return null
        }

        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}