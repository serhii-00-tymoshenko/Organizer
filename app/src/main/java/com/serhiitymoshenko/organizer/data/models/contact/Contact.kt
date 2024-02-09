package com.serhiitymoshenko.organizer.data.models.contact

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String? = null,
    val photo: Bitmap? = null,
    val isNew: Boolean
) : Parcelable