package com.example.myapplication.model

enum class TableStatus {
    AVAILABLE,
    OCCUPIED,
    HELP_REQUESTED,
    PAYING
}

data class Table(
    val id: Int,
    val number: String,
    val status: TableStatus,
    val lastCode: String? = null,
    val lastActivity: String? = null
)
