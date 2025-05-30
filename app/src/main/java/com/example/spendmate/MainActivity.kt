package com.example.spendmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendmate.ui.theme.SpendMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val expenseViewModel: ExpenseViewModel = viewModel()
            SpendMateTheme {
                MyApp(expenseViewModel)
            }
        }
    }
}

@Composable
fun MyApp(expenseViewModel: ExpenseViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "overview") {
        composable("overview") {
            OverviewScreen(
                navigateToAddExpense = { navController.navigate("addExpense") },
                expenseViewModel = expenseViewModel
            )
        }

        composable("addExpense") {
            AddExpenseScreen(
                navigateToOverviewScreen = { navController.navigate("overview") },
                expenseViewModel = expenseViewModel
            )
        }
    }
}
