package com.example.banterbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.banterbox.screen.Navigation
import com.example.banterbox.ui.theme.BanterBoxTheme
import com.example.banterbox.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                  val  navController= rememberNavController()
                  val authViewModel:AuthViewModel= viewModel()
                  BanterBoxTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                              modifier = Modifier.fillMaxSize(),
                              color = MaterialTheme.colorScheme.background
                        ) {
                              Navigation(navController = navController,authViewModel=authViewModel)
                        }
                  }
            }
      }
}
