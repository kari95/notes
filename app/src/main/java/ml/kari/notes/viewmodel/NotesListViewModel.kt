package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.notes.livedata.*
import ml.kari.notes.model.*
import ml.kari.notes.repository.*

class NotesListViewModel(
  private val notesRepository: NotesRepository
): BaseViewModel() {

  val notes: LiveData<List<SavedNote>> = notesRepository.notes

  val openNote: SingleLiveEvent<SavedNote> = SingleLiveEvent()

  override fun onAttachedView() {
    notesRepository.updateNotes()
  }

  fun onRefresh() {
    notesRepository.updateNotes()
  }

  fun onAddClick() {
    openNote.value = null
  }

  fun onNoteClick(note: SavedNote) {
    openNote.value = note
  }
}