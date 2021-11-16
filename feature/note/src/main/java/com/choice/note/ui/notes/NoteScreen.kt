package com.choice.note.ui.notes

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.choice.core.domain.util.Screen
import com.choice.note.ui.NoteViewModel
import com.choice.note.ui.components.NoteItem
import com.choice.note.ui.components.OrderSection
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditNoteScreen.route)
            },
            backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.Add,
                contentDescription = "add_note")
            }
        },
        topBar = {
                 TopAppBar(
                     modifier = Modifier.fillMaxWidth().height(56.dp),
                     title = {Text( textAlign = TextAlign.Start,
                         text = "Suas Anotações",
                         style = MaterialTheme.typography.h6,
                         fontWeight = FontWeight.Normal,
                     )},
                     actions = {
                         IconButton(onClick = {
                             viewModel.onEvent(NoteEvent.ToggleOrderSection)
                         }) {
                             Icon(
                                 imageVector = Icons.Default.Sort,
                                 contentDescription = "sort",
                                 modifier = Modifier.size(18.dp)
                             )
                         }
                     },
                     backgroundColor = MaterialTheme.colors.background,
                     elevation = 0.dp
                 )
        },
        scaffoldState = scaffoldState
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            AnimatedVisibility(
                visible = state.isOrderSelectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NoteEvent.Order(it))
                    }
                )
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn(Modifier.fillMaxSize()){
                items(state.notes){ notes ->
                        NoteItem(
                            note = notes,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.AddEditNoteScreen.route +
                                                "?noteId=${notes.id}&noteColor=${notes.color}")
                                },
                            onDeleteClick = {
                                viewModel.onEvent(NoteEvent.DeleteNote(notes))
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Anotação deletada",
                                        actionLabel = "Desfazer"
                                    )
                                    if(result == SnackbarResult.ActionPerformed){
                                        viewModel.onEvent(NoteEvent.RestoreNote)
                                    }
                                }
                            }
                        )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

}