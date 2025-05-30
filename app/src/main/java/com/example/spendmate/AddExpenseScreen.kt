package com.example.spendmate

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddExpenseScreen(navigateToOverviewScreen: () -> Unit) {

    var expenseValue by remember { mutableStateOf("") }
    var expenseCategory by remember { mutableStateOf("") }
    var expenseDescription by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),

        ) {

        // Activity Title and back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = navigateToOverviewScreen
            ) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
            }

            Text("Add Expense", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input value expense amount section
        Text("Amount")
        TextField(value = expenseValue, onValueChange = { expenseValue = it })



        Text("Expense Category: ")
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            DropdownMenuItem(
                text = { Text("Restaurant") },
                onClick = {}
            )
        }
        TextField(value = expenseCategory, onValueChange = { expenseCategory = it })

        Button(onClick = {}) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
        }


        Text("Expense Description")
        TextField(value = expenseDescription, onValueChange = { expenseDescription = it }, )


        Button(onClick ={
            navigateToOverviewScreen()
        } ) {
            Text("Done")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddExpensePreview() {
    AddExpenseScreen({})

}