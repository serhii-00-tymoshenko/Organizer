package com.serhiitymoshenko.organizer.ui.home.contacts.addcontact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiitymoshenko.organizer.ui.home.contacts.repositories.ContactsRepository
import com.serhiitymoshenko.organizer.data.db.entities.ContactEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AddContactViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {

    fun insertContact(contactEntity: ContactEntity) = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        contactsRepository.insertContact(contactEntity)
    }
}