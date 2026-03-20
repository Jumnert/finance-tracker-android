package com.example.moneysavingtracker.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "demo"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("demo") {
            DemoScreen()
        }
        composable("main") {
            MainScreen()
        }
    }
}
