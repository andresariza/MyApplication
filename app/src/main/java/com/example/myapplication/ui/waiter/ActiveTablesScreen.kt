package com.example.myapplication.ui.waiter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveTablesScreen(viewModel: WaiterViewModel) {
    val neonAmber = Color(0xFFFFB800)
    val darkBg = Color(0xFF0A0A0A)
    val tables by viewModel.tables.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MONITOR DE MESAS", fontSize = 16.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = darkBg, titleContentColor = neonAmber)
            )
        },
        containerColor = darkBg
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tables) { table ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(table.number, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text(table.status, color = if (table.status == "Ocupada") neonAmber else Color.Gray, fontSize = 12.sp)
                        }
                        if (table.lastCode != null) {
                            Column(horizontalAlignment = Alignment.End) {
                                Text("CÓDIGO ACTIVO", color = Color.Gray, fontSize = 10.sp)
                                Text(table.lastCode, color = neonAmber, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
