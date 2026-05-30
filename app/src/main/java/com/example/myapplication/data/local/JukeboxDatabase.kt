package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TableEntity::class], version = 1)
abstract class JukeboxDatabase : RoomDatabase() {
    abstract fun tableDao(): TableDao
}
