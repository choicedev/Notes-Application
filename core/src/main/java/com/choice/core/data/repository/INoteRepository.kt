package com.choice.core.data.repository

import com.choice.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {

    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
}