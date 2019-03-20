package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.notes.livedata.*
import ml.kari.notes.model.*
import ml.kari.notes.repository.*

class NotesListViewModel(
  private val notesRepository: NotesRepository
): BaseViewModel() {

  val notes: LiveData<List<SavedNote>> = Transformations.map(notesRepository.notes, this::mapNotes)

  val openNote: SingleLiveEvent<Note> = SingleLiveEvent()

  override fun onAttachedView() {
    notesRepository.updateNotes()
  }

  fun onRefresh() {
    notesRepository.updateNotes()
  }

  fun onAddClick() {
    openNote.value = Note()
  }

  fun onNoteClick(note: SavedNote) {
    openNote.value = note
  }

  private fun mapNotes(receivedNotes: List<SavedNote>?): List<SavedNote>? {
    if (receivedNotes == null) {
      showConnectionError()
      return notes.value
    }
    return receivedNotes
  }
}