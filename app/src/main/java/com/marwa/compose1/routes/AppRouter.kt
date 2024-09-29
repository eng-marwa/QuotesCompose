package com.marwa.compose1.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marwa.compose1.screens.QuoteDetailsScreen
import com.marwa.compose1.screens.QuotesScreen

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.QUOTES) {
        composable(Routes.QUOTES) {
            QuotesScreen { quoteId ->
                navController.navigate("${Routes.QUOTES}/$quoteId")
            }
        }
        composable(Routes.QUOTE_DETAILS) {
            QuoteDetailsScreen()
        }
    }
}