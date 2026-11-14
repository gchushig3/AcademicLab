package com.lab.app.domain.usecase

import com.lab.app.domain.model.AcademicTask
import com.lab.app.domain.repository.AcademicTaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(private val repository: AcademicTaskRepository) {
    operator fun invoke(): Flow<List<AcademicTask>> {
        return repository.getTasksStream()
    }
}