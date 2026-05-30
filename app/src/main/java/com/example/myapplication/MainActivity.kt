package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.room.Room
import com.example.myapplication.data.WaiterRepository
import com.example.myapplication.data.local.JukeboxDatabase
import com.example.myapplication.navigation.WaiterNavGraph
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.waiter.WaiterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Setup Room and Repository (Simplificado para este ejemplo)
        val db = Room.databaseBuilder(
            applicationContext,
            JukeboxDatabase::class.java, "jukebox-db"
        ).build()
        val repository = WaiterRepository(db.tableDao())
        val viewModel = WaiterViewModel(repository)

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                WaiterNavGraph(viewModel = viewModel)
            }
        }
    }
}
