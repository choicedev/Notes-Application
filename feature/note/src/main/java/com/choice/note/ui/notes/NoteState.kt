package com.choice.note.ui.notes

import com.choice.core.domain.model.Note
import com.choice.core.domain.util.NoteOrder
import com.choice.core.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSelectionVisible: Boolean = false
)