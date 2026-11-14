package com.lab.app.domain.usecase

import com.lab.app.domain.repository.AcademicTaskRepository

class AddTaskUseCase(
    private val repository: AcademicTaskRepository
) {
    suspend operator fun invoke(title: String) {
        val cleanTitle = title.trim()

        if (cleanTitle.length < 5) {
            throw IllegalArgumentException(
                "La regla de dominio exige un mínimo de 5 caracteres."
            )
        }

        repository.addTask(cleanTitle)
    }
}