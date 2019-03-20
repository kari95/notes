package ml.kari.notes.repository

import androidx.lifecycle.*
import kotlinx.coroutines.*
import ml.kari.notes.model.*
import ml.kari.notes.network.*
import retrofit2.*
import timber.log.*
import java.util.*

class NetworkNotesRepository(
  private val notesRequestService: NotesRequestService
): NotesRepository {

  private val _notes: MutableMap<Int, SavedNote> = TreeMap()
  private val mutableNotes: MutableLiveData<List<SavedNote>> = MutableLiveData()

  override val notes: LiveData<List<SavedNote>> = mutableNotes

  override fun updateNotes() {
    launch {
      try {

        val notes = notesRequestService.getNotes().await()
        _notes.clear()
        notes.forEach { note ->
          _notes[note.id] = note
        }
      } catch (e: HttpException) {
        Timber.e(e)
      }
      propagateNotes()
    }
  }

  override fun getNote(id: Int): LiveData<SavedNote> {
    val data = MutableLiveData<SavedNote>()
    if (_notes.containsKey(id)) {
      data.value = _notes[id]
      return data
    }
    launch {
      try {

        val note = notesRequestService.getNote(id).await()
        data.postValue(note)
      } catch (e: HttpException) {
        Timber.e(e)
        data.postValue(null)
      }
      propagateNotes()
    }
    return data
  }

  override fun saveNote(note: Note): LiveData<SavedNote> {
    val data = MutableLiveData<SavedNote>()
    launch {
      try {
        val newNote = if (note is SavedNote) {
           notesRequestService.updateNote(
             note.id,
               Note(note.title)
           ).await()
        } else {
          notesRequestService.addNote(note).await()
        }
        data.postValue(newNote)
        _notes[newNote.id] = newNote
      } catch (e: HttpException) {
        Timber.e(e)
        data.postValue(null)
      }
      propagateNotes()
    }
    return data
  }

  override fun deleteNote(id: Int): LiveData<SavedNote> {

    val data = MutableLiveData<SavedNote>()
    launch {
      try {

        notesRequestService.deleteNote(id).await()
        data.postValue(_notes.remove(id))
      } catch (e: HttpException) {
        Timber.e(e)
        data.postValue(null)
      }
      propagateNotes()
    }
    return data
  }

  private fun propagateNotes() {
    mutableNotes.postValue(_notes.values.toList())
  }
}