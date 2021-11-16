package com.choice.note.ui.notes

import com.choice.core.domain.model.Note
import com.choice.core.domain.util.NoteOrder

sealed class NoteEvent{
    data class Order(val noteOrder: NoteOrder): NoteEvent()
    data class DeleteNote(val note: Note): NoteEvent()
    object RestoreNote: NoteEvent()
    object ToggleOrderSection: NoteEvent()
}
