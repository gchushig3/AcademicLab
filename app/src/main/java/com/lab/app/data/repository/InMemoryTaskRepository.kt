package com.lab.app.data.repository

import com.lab.app.domain.model.AcademicTask
import com.lab.app.domain.repository.AcademicTaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.util.UUID

class InMemoryTaskRepository : AcademicTaskRepository {

    // Simulación de una base de datos en memoria mediante un flujo mutable reactivo
    private val tasksFlow = MutableStateFlow(
        listOf(
            AcademicTask(
                id = UUID.randomUUID().toString(),
                title = "Estudiar Patrón MVVM",
                isCompleted = false
            ),
            AcademicTask(
                id = UUID.randomUUID().toString(),
                title = "Analizar principios de Clean Architecture",
                isCompleted = true
            )
        )
    )

    override fun getTasksStream(): Flow<List<AcademicTask>> = tasksFlow

    override suspend fun addTask(title: String) {
        val newTask = AcademicTask(
            id = UUID.randomUUID().toString(),
            title = title,
            isCompleted = false
        )
        tasksFlow.value = tasksFlow.value + newTask
    }

    override suspend fun toggleTaskCompletion(taskId: String) {
        tasksFlow.value = tasksFlow.value.map { task ->
            if (task.id == taskId) {
                task.copy(isCompleted = !task.isCompleted)
            } else {
                task
            }
        }
    }
}