package com.example.agendacontactos.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.agendacontactos.data.repository.ContactRepository
import com.example.agendacontactos.model.ContactEntity
import com.example.agendacontactos.data.database.DatabaseProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {

    private val _contacts = MutableStateFlow<List<ContactEntity>>(emptyList())
    val contacts: StateFlow<List<ContactEntity>> = _contacts

    init {
        viewModelScope.launch {
            repository.getContacts().collect { list ->
                _contacts.value = list
            }
        }
    }

    fun addContact(contact: ContactEntity) = viewModelScope.launch {
        repository.insert(contact)
    }

    fun updateContact(contact: ContactEntity) = viewModelScope.launch {
        repository.update(contact)
    }

    fun deleteContact(contact: ContactEntity) = viewModelScope.launch {
        repository.delete(contact)
    }

    fun getContactById(id: Int): Flow<ContactEntity?> {
        return repository.getContactById(id)
    }
}


class ContactViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            val dao = DatabaseProvider.getDatabase(context).contactDao()
            val repository = ContactRepository(dao)
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
