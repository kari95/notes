package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.notes.model.*
import ml.kari.notes.repository.*
import androidx.lifecycle.Transformations
import ml.kari.notes.livedata.*

class NoteDetailViewModel(
  private val notesRepository: NotesRepository
): BaseViewModel() {

  private val noteId: MutableLiveData<Int> = MutableLiveData()

  val closeNote: SingleLiveEvent<Unit> = SingleLiveEvent()

  val note: LiveData<Note> = Transformations.switchMap(noteId) { id ->
    notesRepository.getNote(id)
  }

  fun onScreenShowed(noteId: Int) {
    this.noteId.value = noteId
  }

  fun onNoteChanged(text: String) {

    notesRepository.saveNote()
  }

  fun onDeleteClick() {
    closeNote.call()
  }

  fun onSaveClick() {
    closeNote.call()
  }
}