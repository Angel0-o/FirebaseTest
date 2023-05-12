package com.moracoding.firebaseexp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moracoding.firebaseexp.domain.model.User
import com.moracoding.firebaseexp.presentation.home.HomeScreen
import com.moracoding.firebaseexp.presentation.signIn.SignInScreen
import com.moracoding.firebaseexp.presentation.signUp.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignUp.route
    ) {
        composable(route = Screens.SignIn.route) {
            SignInScreen(navController)
        }
        composable(route = Screens.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(route = Screens.Home.route) {
            val user = navController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
            if(user != null) HomeScreen(user = user) else HomeScreen()
        }
    }

}