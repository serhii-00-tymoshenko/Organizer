package com.serhiitymoshenko.organizer.ui.home.contacts.viewmodel

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.contacts.ui.repositories.ContactsRepository

class ContactsViewModel(private val repository: ContactsRepository) : ViewModel() {

    fun getContacts() = repository.getContacts()

    fun getRecentlyAddedContacts() = repository.getRecentlyAddedContacts()
}