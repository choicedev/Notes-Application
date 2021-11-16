package com.choice.core.domain.use_case

data class NoteUseCases(
    val getNotes: GetNotesUseCases,
    val deleteNote: DeleteNotesUseCase,
    val addNote: AddNoteUseCase,
    val getNoteId: GetNotesById
)