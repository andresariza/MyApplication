package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.waiter.*

@Composable
fun WaiterNavGraph(viewModel: WaiterViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel, onNavigateToHome = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable("home") {
            HomeScreen(
                viewModel,
                onNavigateToGenerate = { navController.navigate("generate") },
                onNavigateToTables = { navController.navigate("tables") }
            )
        }
        composable("generate") {
            GenerateCodeScreen(viewModel, onBack = { navController.popBackStack() })
        }
        composable("tables") {
            ActiveTablesScreen(viewModel)
        }
    }
}
