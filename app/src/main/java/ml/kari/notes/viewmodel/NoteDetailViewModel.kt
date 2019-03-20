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
  val loading: LiveData<Boolean> = MutableLiveData()

  init {
    (note as MediatorLiveData).addSource(Transformations.switchMap(noteId) { id ->
      startLoading()
      return@switchMap getNoteById(id)
    }) { result ->
      note.value = result
      stopLoading()
    }
  }

  fun onNoteIdChanged(id: Int) {
    noteId.value = id
  }

  fun onNoteChanged(text: String) {

    note.value?.title = text
  }

  fun onDeleteClick() {
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

  fun onSaveClick() {
    note.value?.let { toSave ->

      startLoading()
      (note as MediatorLiveData).addSource(notesRepository.saveNote(toSave)) { savedNote ->
        stopLoading()
        if (savedNote == null) {
          showConnectionError()
        } else {
          note.value = savedNote
        }
      }
    }
  }

  private fun getNoteById(id: Int): LiveData<Note> {
    if (id == -1) {
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
}