package com.example.productcheckoutsample

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CustomerScreen.route) {
        composable(route = Screen.CustomerScreen.route) {
            CustomerScreen(navController = navController)
        }
        composable(
            route = Screen.ProductScreen.route + "/{customerName}",
            arguments = listOf(
                navArgument("customerName") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            ProductScreen(customerName = entry.arguments?.getString("customerName"))
        }
    }
}
