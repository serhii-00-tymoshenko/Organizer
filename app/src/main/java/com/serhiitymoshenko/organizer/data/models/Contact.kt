package com.serhiitymoshenko.organizer.data.models

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "email")
    val email: String? = null,
    @ColumnInfo(name = "photo")
    val photo: Bitmap? = null,
    @ColumnInfo(name = "is_new")
    val isNew: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)
