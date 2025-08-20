package com.example.agendacontactos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agendacontactos.ui.theme.screens.contactDetail.ContactDetailScreen
import com.example.agendacontactos.ui.theme.screens.contactForm.ContactFormScreen
import com.example.agendacontactos.ui.theme.screens.contactForm.ContactEditScreen
import com.example.agendacontactos.ui.theme.screens.home.HomeScreen
import com.example.agendacontactos.ui.theme.screens.splash.SplashScreen
import com.example.agendacontactos.viewmodel.ContactViewModel
import com.example.agendacontactos.viewmodel.ContactViewModelFactory

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }


        composable(Screen.Home.route) {
            HomeScreen(
                onAddClick = { navController.navigate(Screen.ContactForm.route) },
                onContactClick = { contact ->
                    navController.navigate(Screen.ContactDetail.createRoute(contact.id))
                }
            )
        }


        composable(Screen.ContactForm.route) {
            val context = LocalContext.current
            val viewModel: ContactViewModel = viewModel(
                factory = ContactViewModelFactory(context)
            )
            ContactFormScreen(navController, viewModel)
        }


        composable(Screen.ContactDetail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            val context = LocalContext.current
            val viewModel: ContactViewModel = viewModel(
                factory = ContactViewModelFactory(context)
            )
            id?.let { ContactDetailScreen(navController, contactId = it) }
        }


        composable(Screen.ContactEdit.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            val context = LocalContext.current
            val viewModel: ContactViewModel = viewModel(
                factory = ContactViewModelFactory(context)
            )
            id?.let { ContactEditScreen(navController, contactId = it) }
        }
    }
}
