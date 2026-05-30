package com.example.myapplication.ui.waiter

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.WaiterRepository
import com.example.myapplication.data.local.TableEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WaiterViewModel(private val repository: WaiterRepository) : ViewModel() {
    
    // B1: Login State
    var employeeId by mutableStateOf("")
    var password by mutableStateOf("")
    var loginError by mutableStateOf<String?>(null)

    // B3: Generation State
    var selectedTable by mutableStateOf<TableEntity?>(null)
    var selectedCredits by mutableStateOf(1)
    var generatedPin by mutableStateOf("")

    // B4: Active Tables State
    val tables: StateFlow<List<TableEntity>> = repository.allTables.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            repository.initializeTables()
        }
    }

    fun onLoginClick(onSuccess: () -> Unit) {
        if (employeeId.isNotEmpty() && password.isNotEmpty()) {
            onSuccess()
        } else {
            loginError = "Campos obligatorios"
        }
    }

    fun generatePin(table: TableEntity) {
        val pin = (100000..999999).random().toString()
        generatedPin = pin
        viewModelScope.launch {
            repository.updateTable(table.copy(lastCode = pin, status = "Ocupada"))
        }
    }
}
