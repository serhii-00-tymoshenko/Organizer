package com.serhiitymoshenko.organizer.data.db.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.serhiitymoshenko.organizer.utils.toByteArray
import com.serhiitymoshenko.organizer.utils.tooBitmap
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

@TypeConverters
class BitmapConverter {
    @TypeConverter
    fun bitmapToByteArray(bitmap: Bitmap?): ByteArray? = bitmap?.toByteArray()

    @TypeConverter
    fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap? = byteArray?.tooBitmap()
}