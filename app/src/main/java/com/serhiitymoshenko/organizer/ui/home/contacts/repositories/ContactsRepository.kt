package com.serhiitymoshenko.organizer.ui.home.contacts.repositories

import com.serhiitymoshenko.organizer.data.db.daos.ContactsDao
import com.serhiitymoshenko.organizer.data.db.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

class ContactsRepository(private val contactsDao: ContactsDao) {


    fun getContacts(): Flow<List<ContactEntity>> = contactsDao.getContacts()


    fun getRecentlyAddedContacts(): Flow<List<ContactEntity>> = contactsDao.getRecentlyAddedContacts()


    suspend fun updateContact(contactEntity: ContactEntity) = contactsDao.updateContact(contactEntity)


    suspend fun deleteContact(contactEntity: ContactEntity) = contactsDao.deleteContact(contactEntity)


    suspend fun insertContact(contactEntity: ContactEntity) = contactsDao.insertContact(contactEntity)
}