package com.example.myapplication.ui.waiter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateCodeScreen(viewModel: WaiterViewModel, onBack: () -> Unit) {
    val neonAmber = Color(0xFFFFB800)
    val darkBg = Color(0xFF0A0A0A)
    val tables by viewModel.tables.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GENERAR ACCESO", fontSize = 16.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = darkBg, titleContentColor = neonAmber)
            )
        },
        containerColor = darkBg
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("PASO 01: SELECCIONAR MESA", color = Color.Gray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.height(200.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tables) { table ->
                    val isSelected = viewModel.selectedTable?.id == table.id
                    Button(
                        onClick = { viewModel.selectedTable = table },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) neonAmber else Color(0xFF1A1A1A)
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(table.number, color = if (isSelected) Color.Black else Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("PASO 02: CRÉDITOS", color = Color.Gray, fontSize = 12.sp)
            Slider(
                value = viewModel.selectedCredits.toFloat(),
                onValueChange = { viewModel.selectedCredits = it.toInt() },
                valueRange = 1f..10f,
                steps = 9,
                colors = SliderDefaults.colors(thumbColor = neonAmber, activeTrackColor = neonAmber)
            )
            Text("${viewModel.selectedCredits} Solicitudes", color = Color.White, modifier = Modifier.align(Alignment.End))

            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = { viewModel.selectedTable?.let { viewModel.generatePin(it) } },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = neonAmber),
                enabled = viewModel.selectedTable != null
            ) {
                Text("GENERAR PIN", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            if (viewModel.generatedPin.isNotEmpty()) {
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1A1A1A))
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = viewModel.generatedPin,
                        color = neonAmber,
                        fontSize = 48.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}
