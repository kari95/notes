package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.notes.model.*
import ml.kari.notes.repository.*

class NotesListViewModel(
  private val notesRepository: NotesRepository
): BaseViewModel() {

  val notes: LiveData<List<Note>> = notesRepository.notes

  fun onScreenShowed() {
    notesRepository.updateNotes()
  }

  fun onNoteClick(note: Note) {

  }
}