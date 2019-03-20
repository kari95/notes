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

  val note: LiveData<Note> = MediatorLiveData()

  val closeNote: SingleLiveEvent<Unit> = SingleLiveEvent()
  val loading: LiveData<Boolean> = MutableLiveData()

  init {
    (note as MediatorLiveData).addSource(Transformations.switchMap(noteId) { id ->
      (loading as MutableLiveData).value = true
      return@switchMap getNoteById(id)
    }) { result ->
      note.value = result
      (loading as MutableLiveData).value = false
    }
  }

  fun onNoteIdChanged(noteId: Int) {
    this.noteId.value = noteId
  }

  fun onNoteChanged(text: String) {

    note.value?.title = text
  }

  fun onDeleteClick() {
    note.value?.let { toDelete ->

      if (toDelete is SavedNote) {

        (loading as MutableLiveData).value = true

        (note as MediatorLiveData).addSource(notesRepository.deleteNote(toDelete.id)) {
          loading.value = false
          closeNote.call()
        }
      } else {
        closeNote.call()
      }
    }
  }

  fun onSaveClick() {
    note.value?.let { toSave ->

      (loading as MutableLiveData).value = true

      (note as MediatorLiveData).addSource(notesRepository.saveNote(toSave)) { result ->
        note.value = result
        loading.value = false
      }
    }
  }

  private fun getNoteById(id: Int): LiveData<Note> {
    if (id == -1) {
      return MutableLiveData<Note>().apply { postValue(Note()) }
    }
    return Transformations.map(notesRepository.getNote(id)) { it }
  }
}