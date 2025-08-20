package com.example.agendacontactos.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object ContactForm : Screen("contact_form")
    object ContactDetail : Screen("contact_detail/{id}") {
        fun createRoute(id: Int) = "contact_detail/$id"
    }
    object ContactEdit : Screen("contact_edit/{id}") {
        fun createRoute(id: Int) = "contact_edit/$id"
    }
}
