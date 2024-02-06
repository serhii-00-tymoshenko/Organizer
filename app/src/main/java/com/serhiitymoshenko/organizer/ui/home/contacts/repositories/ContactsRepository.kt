package com.serhiitymoshenko.contacts.ui.repositories

import com.serhiitymoshenko.organizer.data.db.daos.ContactsDao
import com.serhiitymoshenko.organizer.data.models.Contact
import kotlinx.coroutines.flow.Flow

class ContactsRepository(private val contactsDao: ContactsDao) {


    fun getContacts(): Flow<List<Contact>> = contactsDao.getContacts()


    fun getRecentlyAddedContacts(): Flow<List<Contact>> = contactsDao.getRecentlyAddedContacts()


    suspend fun updateContact(contact: Contact) = contactsDao.updateContact(contact)


    suspend fun deleteContact(contact: Contact) = contactsDao.deleteContact(contact)


    suspend fun insertContact(contact: Contact) = contactsDao.insertContact(contact)
}