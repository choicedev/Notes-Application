package com.choice.edit_add_notes.ui.edit_add

import androidx.compose.ui.focus.FocusState
sealed class NoteEvent {
    data class EnteredTitle(val value: String): NoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): NoteEvent()
    data class EnteredContent(val value: String): NoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): NoteEvent()
    data class ChangeColor(val color: Int): NoteEvent()
    object SaveNote: NoteEvent()

}
