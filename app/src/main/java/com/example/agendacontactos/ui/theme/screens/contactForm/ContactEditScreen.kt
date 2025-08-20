package com.example.agendacontactos.ui.theme.screens.contactForm

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agendacontactos.model.ContactEntity
import com.example.agendacontactos.viewmodel.ContactViewModel

@Composable
fun ContactEditScreen(
    navController: NavController,
    contactId: Int,
    viewModel: ContactViewModel = viewModel()
) {
    val contact = viewModel.getContactById(contactId).collectAsState(initial = null).value

    var name by remember { mutableStateOf(contact?.name ?: "") }
    var lastName by remember { mutableStateOf(contact?.lastName ?: "") }
    var phone by remember { mutableStateOf(contact?.phone ?: "") }
    var email by remember { mutableStateOf(contact?.email ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (contact != null) {
                    val updatedContact = contact.copy(
                        name = name,
                        lastName = lastName,
                        phone = phone,
                        email = email
                    )
                    viewModel.updateContact(updatedContact)
                }
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar cambios")
        }
    }
}
