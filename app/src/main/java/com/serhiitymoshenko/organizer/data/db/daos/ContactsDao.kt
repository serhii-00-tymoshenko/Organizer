package com.serhiitymoshenko.organizer.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.serhiitymoshenko.organizer.data.models.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts")
    fun getContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE is_new = 1")
    fun getRecentlyAddedContacts(): Flow<List<Contact>>

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)
}