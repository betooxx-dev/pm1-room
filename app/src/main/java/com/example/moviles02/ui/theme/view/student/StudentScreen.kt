package com.example.moviles02.ui.theme.view.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviles02.ui.theme.viewmodel.StudentViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StudentScreen(
    viewModel: StudentViewModel = hiltViewModel()
) {
    val students by viewModel.students.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { viewModel.loadStudents() }) {
                Text("Cargar Estudiantes")
            }

            Button(onClick = { viewModel.clearStudents() }) {
                Text("Limpiar Lista")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (students.isEmpty()) {
            Text(
                "No hay estudiantes cargados.",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn {
                items(students) { student ->
                    StudentItem(student)
                }
            }
        }
    }
}