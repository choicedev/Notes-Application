package com.choice.noteapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.choice.core.base.CleanArchitectureNoteAppTheme
import com.choice.core.domain.util.Screen
import com.choice.edit_add_notes.ui.EditNoteViewModel
import com.choice.edit_add_notes.ui.edit_add.EditNoteScreen
import com.choice.note.ui.NoteViewModel
import com.choice.note.ui.notes.NotesScreen

@AndroidEntryPoint
    class MainActivity : ComponentActivity() {

        @ExperimentalAnimationApi
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureNoteAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NoteScreen.route
                    ) {
                        composable(route = Screen.NoteScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                              EditNoteScreen(
                                navController = navController,
                                noteColor = color

                            )
                        }
                    }
                }
            }
        }
        }
}

