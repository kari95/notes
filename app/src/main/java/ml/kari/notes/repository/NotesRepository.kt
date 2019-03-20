package ml.kari.notes.repository

import androidx.lifecycle.*
import ml.kari.notes.model.*

interface NotesRepository {

  val notes: LiveData<List<SavedNote>>

  fun updateNotes()

  fun getNote(id: Int): LiveData<SavedNote>

  fun saveNote(note: Note): LiveData<SavedNote>

  fun deleteNote(id: Int): LiveData<SavedNote>
}