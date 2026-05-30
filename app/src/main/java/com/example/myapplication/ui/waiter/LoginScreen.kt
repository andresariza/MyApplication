package com.example.myapplication.ui.waiter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(viewModel: WaiterViewModel, onNavigateToHome: () -> Unit) {
    val neonAmber = Color(0xFFFFB800)
    val darkBg = Color(0xFF0A0A0A)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBg)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "NEON BEATS",
            color = neonAmber,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "ACCESO MESERO",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = viewModel.employeeId,
            onValueChange = { viewModel.employeeId = it },
            label = { Text("ID Empleado") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = neonAmber,
                unfocusedBorderColor = Color.DarkGray,
                focusedLabelColor = neonAmber
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = neonAmber,
                unfocusedBorderColor = Color.DarkGray,
                focusedLabelColor = neonAmber
            )
        )

        if (viewModel.loginError != null) {
            Text(text = viewModel.loginError!!, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { viewModel.onLoginClick(onNavigateToHome) },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = neonAmber)
        ) {
            Text("ENTRAR", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}
