package com.example.agendacontactos.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.agendacontactos.data.dao.ContactDao
import com.example.agendacontactos.model.ContactEntity

@Database(
    entities = [ContactEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
