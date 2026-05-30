package com.example.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tables")
data class TableEntity(
    @PrimaryKey val id: Int,
    val number: String,
    val status: String,
    val lastCode: String? = null,
    val lastActivity: String? = null
)
