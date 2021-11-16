package com.choice.core.domain.use_case

import com.choice.core.base.IBaseUseCase
import com.choice.core.data.repository.INoteRepository
import com.choice.core.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteNotesUseCase (
    private val repository: INoteRepository
) {
    suspend operator fun invoke(input: Note) {
        repository.deleteNote(input)
    }
}