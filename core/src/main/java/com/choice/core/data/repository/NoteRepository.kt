package com.choice.core.data.repository

import com.choice.core.data.source.NoteDao
import com.choice.core.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteRepository(
    private val dao: NoteDao
) : INoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }


    override suspend fun getNoteById(id: Int): Note?{
        return dao.getNotesById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note){
        dao.deleteNote(note)
    }
}