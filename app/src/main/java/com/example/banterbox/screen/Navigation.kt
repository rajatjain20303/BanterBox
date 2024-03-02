package com.example.banterbox.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.banterbox.viewmodel.AuthViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
            composable(Screen.ChatRoomsScreen.route) {
                  ChatRoomListScreen {
                        navController.navigate("${Screen.ChatScreen.route}/${it.id}")
                  }
            }

            composable("${Screen.ChatScreen.route}/{roomId}") {
                  val roomId: String = it
                        .arguments?.getString("roomId") ?: ""
                  ChatScreen(roomId = roomId)
            }
      }
}