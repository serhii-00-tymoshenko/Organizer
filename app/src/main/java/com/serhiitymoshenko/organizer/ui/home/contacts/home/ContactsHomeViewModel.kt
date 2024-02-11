package com.serhiitymoshenko.organizer.ui.home.contacts.home

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.organizer.ui.home.contacts.repositories.ContactsRepository
import com.serhiitymoshenko.organizer.data.models.converters.fromEntitiesToContacts
import kotlinx.coroutines.flow.map

class ContactsHomeViewModel(private val repository: ContactsRepository) : ViewModel() {

    fun getContacts() = repository.getContacts().map { entities -> entities.fromEntitiesToContacts() }

    fun getRecentlyAddedContacts() = repository.getRecentlyAddedContacts().map { entities -> entities.fromEntitiesToContacts() }
}