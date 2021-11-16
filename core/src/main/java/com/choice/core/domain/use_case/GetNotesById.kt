package com.choice.core.domain.use_case

import com.choice.core.base.IBaseUseCase
import com.choice.core.data.repository.INoteRepository
import com.choice.core.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNotesById(
    private val repository: INoteRepository
){
    suspend operator fun invoke(input: Int): Note? {
        return repository.getNoteById(input)
    }

}