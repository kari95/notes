package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.notes.model.*
import ml.kari.notes.repository.*
import ml.kari.notes.R
import ml.kari.notes.livedata.*

class NoteDetailViewModel(
  private val notesRepository: NotesRepository
): BaseViewModel() {

  private val noteId: MutableLiveData<Int> = MutableLiveData()

  val note: LiveData<Note> = MediatorLiveData()

  val closeNote: SingleLiveEvent<Unit> = SingleLiveEvent()
  val showConfirmDialog: SingleLiveEvent<(Boolean) -> Unit> = SingleLiveEvent()
  val loading: LiveData<Boolean> = MutableLiveData()

  private var noteChanged: Boolean = false

  init {
    (note as MediatorLiveData).addSource(Transformations.switchMap(noteId) { id ->
      startLoading()
      return@switchMap getNoteById(id)
    }) { result ->
      note.value = result
      noteChanged = false
      stopLoading()
    }
  }

  fun onNoteIdChanged(id: Int) {
    noteId.value = id
  }

  fun onNoteChanged(text: String) {
    if (text != note.value?.title) {
      noteChanged = true
    }
    note.value?.title = text
  }

  fun onDeleteClick() {

    showConfirmationDialog { confirmed ->
      if (confirmed) {
        deleteNote()
      }
    }
  }

  fun onSaveClick() {
    note.value?.let { toSave ->

      startLoading()
      (note as MediatorLiveData).addSource(notesRepository.saveNote(toSave)) { savedNote ->
        stopLoading()
        if (savedNote == null) {
          showConnectionError()
        } else {
          note.value = savedNote
          noteChanged = false
        }
      }
    }
  }

  fun onBackClick() {
    if (!noteChanged) {
      closeDetail()
    } else {
      showConfirmationDialog { confirmed ->
        if (confirmed) {
          closeDetail()
        }
      }
    }
  }

  private fun deleteNote() {
    note.value?.let { toDelete ->

      if (toDelete is SavedNote) {

        startLoading()
        (note as MediatorLiveData).addSource(
          notesRepository.deleteNote(toDelete.id)) { removedNote ->
          stopLoading()
          if (removedNote == null) {
            showConnectionError()
          } else {
            closeDetail()
          }
        }
      } else {
        closeDetail()
      }
    }
  }

  private fun getNoteById(id: Int): LiveData<Note> {
    if (id == -1) {
      noteChanged = true
      return MutableLiveData<Note>().apply { postValue(Note()) }
    }
    return Transformations.map(notesRepository.getNote(id)) { it }
  }

  private fun startLoading() {
    (loading as MutableLiveData).value = true
  }

  private fun stopLoading() {
    (loading as MutableLiveData).value = false
  }

  private fun closeDetail() {
    closeNote.call()
  }

  private fun showConfirmationDialog(callback: (Boolean) -> Unit) {
    showConfirmDialog.value = callback
  }
}