package com.example.myapplication.data

import com.example.myapplication.data.local.TableDao
import com.example.myapplication.data.local.TableEntity
import kotlinx.coroutines.flow.Flow

class WaiterRepository(private val tableDao: TableDao) {
    val allTables: Flow<List<TableEntity>> = tableDao.getAllTables()

    suspend fun updateTable(table: TableEntity) {
        tableDao.updateTable(table)
    }

    suspend fun initializeTables() {
        val initialTables = (1..20).map { id ->
            TableEntity(id = id, number = "T$id", status = "Disponible")
        }
        tableDao.insertTables(initialTables)
    }
}
