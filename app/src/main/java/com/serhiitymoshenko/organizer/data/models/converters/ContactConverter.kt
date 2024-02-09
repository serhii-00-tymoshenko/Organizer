package com.serhiitymoshenko.organizer.data.models.converters

import com.serhiitymoshenko.organizer.data.db.entities.ContactEntity
import com.serhiitymoshenko.organizer.data.models.contact.Contact

fun ContactEntity.fromEntityToContact() =
    Contact(
        this.id ?: 0,
        this.firstName,
        this.lastName,
        this.phoneNumber,
        this.email,
        this.photo,
        this.isNew
    )

fun List<ContactEntity>.fromEntitiesToContacts() =
    this.map { it.fromEntityToContact() }

fun Contact.toContactEntity() =
    ContactEntity(
        null ?: this.id,
        this.firstName,
        this.lastName,
        this.phoneNumber,
        this.email,
        this.photo,
        this.isNew
    )