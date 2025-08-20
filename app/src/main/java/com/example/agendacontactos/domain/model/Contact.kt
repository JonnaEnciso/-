package com.example.agendacontactos.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val lastName: String?,
    val phone: String,
    val email: String,
    val address: String?,
    val company: String?,
    val notes: String?
)
