package com.example.spendmate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ExpenseViewModel: ViewModel() {
    var expenseList = mutableListOf<Expense>()
    var currentBalance = mutableStateOf(0.0)
    var totalSpent = mutableStateOf(0.0)

    fun addExpense (expense: Expense) {
        expenseList.add(expense)
        currentBalance.value -= expense.expenseString.toDouble()
        totalSpent.value += expense.expenseString.toDouble()
    }
}