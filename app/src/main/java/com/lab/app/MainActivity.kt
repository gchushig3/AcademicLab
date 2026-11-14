package com.lab.app

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.lab.app.data.repository.InMemoryTaskRepository
import com.lab.app.domain.usecase.AddTaskUseCase
import com.lab.app.domain.usecase.GetTasksUseCase
import com.lab.app.ui.presentation.tasks.AcademicTaskApp
import com.lab.app.ui.presentation.tasks.AcademicTaskViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // 1. Orquestación manual de dependencias
        val repository = InMemoryTaskRepository()

        val getTasksUseCase = GetTasksUseCase(
            repository = repository
        )

        val addTaskUseCase = AddTaskUseCase(
            repository = repository
        )

        // 2. Creación del ViewModel
        val viewModel = AcademicTaskViewModel(
            getTasksUseCase = getTasksUseCase,
            addTaskUseCase = addTaskUseCase,
            repository = repository
        )

        // 3. Renderización de la interfaz
        setContent {
            AcademicTaskApp(
                viewModel = viewModel
            )
        }
    }
}
