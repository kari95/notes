package ml.kari.notes.repository

import androidx.lifecycle.*
import ml.kari.notes.model.*

interface NotesRepository {

  val notes: LiveData<List<Note>>

  fun updateNotes()

  fun getNote(id: Int): LiveData<Note>

  fun saveNote(note: Note): LiveData<Note>
}