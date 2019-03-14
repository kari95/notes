package ml.kari.notes.repository

import androidx.lifecycle.*
import ml.kari.notes.model.*
import ml.kari.notes.network.*

class CachedNotesRepository(
  private val notesRequestService: NotesRequestService
): NotesRepository {

  private val _notes: MutableMap<Int, Note> = mutableMapOf()
  private val mutableNotes: MutableLiveData<List<Note>> = MutableLiveData()

  override val notes: LiveData<List<Note>> = mutableNotes

  override fun updateNotes() {
    notesRequestService.getNotes().observeForever { notes ->
      _notes.clear()
      notes.forEach { note ->
        _notes[note.id] = note
      }
    }
    propagateNotes()
  }

  override fun saveNote(note: Note) {

  }

  private fun propagateNotes() {
    mutableNotes.postValue(_notes.values.toList())
  }
}