package com.example.spendmate

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun OverviewScreen(
    navigateToAddExpense: () -> Unit,
    expenseViewModel: ExpenseViewModel
) {

    val expenseList = expenseViewModel.expenseList
    val currentBalance = expenseViewModel.currentBalance
    val totalSpent = expenseViewModel.totalSpent

    var expenseIncomeTabs = listOf("EXPENSE", "INCOME")
    var expenseIncomeSelected by remember { mutableStateOf(0) }
    var selectedTab by remember { mutableStateOf(2) }
    var tabs = listOf("Day", "Month", "Year")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Your Current Balance: ")
        Text("€ $currentBalance", fontWeight = FontWeight.Bold, fontSize = 24.sp)

        // Expense Income Tab
        Spacer(modifier = Modifier.height(8.dp))
        TabRow(selectedTabIndex = expenseIncomeSelected) {
            expenseIncomeTabs.forEachIndexed { i, title ->
                Tab(
                    selected = i == expenseIncomeSelected,
                    onClick = { expenseIncomeSelected = i },
                    text = { Text(title) })
            }
        }

        // Day, Month, Year Tab
        Spacer(modifier = Modifier.height(8.dp))
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { i, title ->
                Tab(
                    selected = i == selectedTab,
                    onClick = { selectedTab = i },
                    text = { Text(title) }
                )
            }
        }

        // Main center display, add expenses display total spent
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            // Move back and forward dates
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
                }

                Text("2024", fontSize = 18.sp)

                IconButton(onClick = {}) {
                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
                }

            }

            // Total spent section display and add expense button
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Spent: €$totalSpent",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Button(onClick = {
                    navigateToAddExpense()
                }, modifier = Modifier.padding(8.dp)) {
                    Text("+")
                }
            }
        }

        // Display all expenses added
        val context = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(expenseList) { item ->
                ExpenseListItem(item, onClickExpand = {
                    Toast.makeText(context, "Expanded", Toast.LENGTH_LONG).show()
                })


            }

        }
    }
}


@Composable
fun ExpenseListItem(
    item: Expense,
    onClickExpand: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(20)
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(item.category, modifier = Modifier.padding(8.dp))
        Text("€${item.expenseString}", modifier = Modifier.padding(8.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun OverviewPreview() {
    //OverviewScreen({})
}
