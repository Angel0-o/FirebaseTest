package com.moracoding.firebaseexp.presentation.navigation

sealed class Screens(val route: String) {
    object SignIn : Screens(route = "SignIn_Screen")
    object SignUp : Screens(route = "SignUp_Screen")
    object Home : Screens(route = "Home_Screen")

}
