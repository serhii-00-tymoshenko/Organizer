package com.serhiitymoshenko.organizer.data.entities.contact

data class Contact(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String? = null,
    val photoBytes: ByteArray? = null,
    val id: Int? = null
)
