package com.example.banterbox.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.banterbox.viewmodel.AuthViewModel

@Composable
fun Navigation(navController: NavHostController,authViewModel: AuthViewModel ) {
      NavHost(
            navController = navController,
            startDestination = Screen.SignupScreen.route
      ) {
            composable(Screen.SignupScreen.route) {
                  SignUpScreen(
                        authViewModel = authViewModel,
                        onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) }
                  )
            }
            composable(Screen.LoginScreen.route) {
                  LoginScreen(authViewModel=authViewModel,
                        onNavigateToSignUp = { navController.navigate(Screen.SignupScreen.route) }
                  ){
                        navController.navigate(Screen.ChatRoomsScreen.route)
                  }
            }
      }
}