package com.example.agendacontactos.ui.theme.screens.contactDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agendacontactos.model.ContactEntity
import com.example.agendacontactos.navigation.Screen
import com.example.agendacontactos.viewmodel.ContactViewModel

@Composable
fun ContactDetailScreen(
    navController: NavController,
    contactId: Int,
    viewModel: ContactViewModel = viewModel()
) {
    val contact = viewModel.getContactById(contactId).collectAsState(initial = null).value

    if (contact == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("Contacto no encontrado")
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nombre: ${contact.name} ${contact.lastName ?: ""}", style = MaterialTheme.typography.titleLarge)
            Text(text = "Teléfono: ${contact.phone}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Email: ${contact.email}", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {
                    navController.navigate(Screen.ContactEdit.createRoute(contact.id))
                }) {
                    Text("Editar")
                }

                Button(
                    onClick = {
                        viewModel.deleteContact(contact)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Eliminar")
                }
            }
        }
    }
}
