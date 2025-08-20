package com.example.agendacontactos.ui.theme.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agendacontactos.model.ContactEntity
import com.example.agendacontactos.viewmodel.ContactViewModel
import com.example.agendacontactos.viewmodel.ContactViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAddClick: () -> Unit = {},
    onContactClick: (ContactEntity) -> Unit = {}
) {
    val context = LocalContext.current
    val viewModel: ContactViewModel = viewModel(
        factory = ContactViewModelFactory(context)
    )

    val contacts by viewModel.contacts.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Contacto")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (contacts.isEmpty()) {
                Text(
                    text = "No hay contactos guardados",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn {
                    items(contacts, key = { it.id }) { contact ->
                        val dismissState = rememberSwipeToDismissBoxState()

                        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart ||
                            dismissState.currentValue == SwipeToDismissBoxValue.StartToEnd
                        ) {
                            viewModel.deleteContact(contact)
                        }

                        SwipeToDismissBox(
                            state = dismissState,
                            backgroundContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                                }
                            },
                            content = {
                                ListItem(
                                    headlineContent = {
                                        Text("${contact.name} ${contact.lastName ?: ""}")
                                    },
                                    supportingContent = {
                                        Text(contact.phone)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onContactClick(contact) }
                                        .padding(8.dp)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
