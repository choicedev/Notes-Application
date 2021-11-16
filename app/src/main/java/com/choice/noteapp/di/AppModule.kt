package com.choice.noteapp.di

import android.app.Application
import androidx.room.Room
import com.choice.core.data.repository.INoteRepository
import com.choice.core.data.repository.NoteRepository
import com.choice.core.data.source.NoteDatabase
import com.choice.core.domain.use_case.*
import com.choice.core.domain.use_case.GetNotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): INoteRepository {
        return NoteRepository(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUsesCases(repository: INoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotesUseCases(repository),
            deleteNote = DeleteNotesUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNoteId = GetNotesById(repository)
        )
    }
}