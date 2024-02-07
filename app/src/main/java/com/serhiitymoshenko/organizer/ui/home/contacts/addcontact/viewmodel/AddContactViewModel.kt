package com.serhiitymoshenko.organizer.ui.home.contacts.addcontact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiitymoshenko.contacts.ui.repositories.ContactsRepository
import com.serhiitymoshenko.organizer.data.models.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AddContactViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {

    fun insertContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        contactsRepository.insertContact(contact)
    }
}