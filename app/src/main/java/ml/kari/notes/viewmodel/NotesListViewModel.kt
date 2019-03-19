package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.notes.livedata.*
import ml.kari.notes.model.*
import ml.kari.notes.repository.*

class NotesListViewModel(
  private val notesRepository: NotesRepository
): BaseViewModel() {

  val notes: LiveData<List<Note>> = notesRepository.notes

  val openNote: SingleLiveEvent<Note> = SingleLiveEvent()

  fun onScreenShowed() {
    notesRepository.updateNotes()
  }

  fun onRefresh() {
    notesRepository.updateNotes()
  }

  fun onAddClick() {
    openNote.value = Note()
  }

  fun onNoteClick(note: Note) {
    openNote.value = note
  }
}