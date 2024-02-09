package com.serhiitymoshenko.organizer.data.db.converters

import android.graphics.Bitmap
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.serhiitymoshenko.organizer.utils.toBitmap
import com.serhiitymoshenko.organizer.utils.toByteArray

@TypeConverters
class BitmapConverter {
    @TypeConverter
    fun bitmapToByteArray(bitmap: Bitmap?): ByteArray? = bitmap?.toByteArray()

    @TypeConverter
    fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap? = byteArray?.toBitmap()
}