package com.example.agendacontactos.data.repository

import com.example.agendacontactos.data.dao.ContactDao
import com.example.agendacontactos.model.ContactEntity
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val dao: ContactDao) {

    fun getContacts(): Flow<List<ContactEntity>> = dao.getAllContacts()

    fun getContactById(id: Int): Flow<ContactEntity?> = dao.getContactById(id)

    suspend fun insert(contact: ContactEntity) = dao.insert(contact)

    suspend fun update(contact: ContactEntity) = dao.update(contact)

    suspend fun delete(contact: ContactEntity) = dao.delete(contact)
}
