package com.example.spendmate

import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Expense(
    var id: Int,
    var category: String,
    var expenseString: String,
    var expenseDescription: String
)

@Composable
fun OverviewScreen() {
    var expenseList by remember { mutableStateOf(listOf<Expense>()) }
    var currentBalance by remember { mutableStateOf("0") }
    var categoryOption by remember { mutableStateOf("") }
    var newExpenseValue by remember { mutableStateOf("") }
    var addExpenseBox by remember { mutableStateOf(false) }
    var valueSpent by remember { mutableStateOf("0") }

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

        // Main center display, add expenses total spent
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
                    "Spent: €$valueSpent",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Button(onClick = {
                    addExpenseBox = true
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

    if (addExpenseBox) {
        AlertDialog(
            onDismissRequest = {
                addExpenseBox = false
                categoryOption = ""
                newExpenseValue = ""
            },
            confirmButton = {
                Button(onClick = {
                    addExpenseBox = false
                    categoryOption = ""
                    newExpenseValue = ""
                }) {
                    Text("Cancel")

                }

                Button(onClick = {
                    if (newExpenseValue.isNotBlank() && categoryOption.isNotBlank()) {
                        val newExpense =
                            Expense(id = expenseList.size + 1, categoryOption, newExpenseValue, "")
                        currentBalance =
                            (currentBalance.toDouble() - newExpenseValue.toDouble()).toString()
                        valueSpent = (valueSpent.toDouble() + newExpenseValue.toDouble()).toString()
                        expenseList = expenseList + newExpense
                    }
                    categoryOption = ""
                    newExpenseValue = ""
                    addExpenseBox = false
                }) {
                    Text("Confirm")
                }

            },
            title = { Text("Add Expense") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                ) {
                    OutlinedTextField(
                        value = categoryOption,
                        onValueChange = { categoryOption = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)

                    )

                    OutlinedTextField(
                        value = newExpenseValue,
                        onValueChange = { newExpenseValue = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                }
            }

        )

    }
}


@Composable
fun ExpenseListItem(
    item: Expense,
    onClickExpand: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(item.category, modifier = Modifier.padding(8.dp))
            Text("€${item.expenseString}", modifier = Modifier.padding(8.dp))
            IconButton(onClick = {
                onClickExpand()
            }) { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null) }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun OverviewPreview() {
    OverviewScreen()
}
