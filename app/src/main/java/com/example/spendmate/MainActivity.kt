package com.example.spendmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendmate.ui.theme.SpendMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SpendMateTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "overviewscreen") {
        composable("overviewscreen") {
            OverviewScreen {
                navController.navigate("addexpensescreen")
            }
        }
        composable("addexpensescreen") {
            AddExpenseScreen {
                navController.navigate("overviewscreen")
            }
        }
    }
}