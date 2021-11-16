package com.choice.core.domain.use_case

import com.choice.core.base.IBaseUseCase
import com.choice.core.data.repository.INoteRepository
import com.choice.core.domain.model.InvalidNoteException
import com.choice.core.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddNoteUseCase(
    private val repository: INoteRepository
) {
    suspend operator fun invoke(input: Note) {
        when {
            input.title.isBlank() -> {
                throw InvalidNoteException("O titulo não pode ficar em branco")
            }
            input.content.isBlank() -> {
                throw InvalidNoteException("A descrição não pode ficar em branco")
            }
            else -> {
                repository.insertNote(input)
            }
        }
    }
}