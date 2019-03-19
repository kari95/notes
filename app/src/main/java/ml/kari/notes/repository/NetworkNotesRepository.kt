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

  private val _notes: MutableMap<Int, Note> = TreeMap()
  private val mutableNotes: MutableLiveData<List<Note>> = MutableLiveData()

  override val notes: LiveData<List<Note>> = mutableNotes

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

  override fun getNote(id: Int): LiveData<Note> {
    val data = MutableLiveData<Note>()
    launch {
      try {

        val note = notesRequestService.getNote(id).await()
        data.value = note
      } catch (e: HttpException) {
        Timber.e(e)
      }
      propagateNotes()
    }
    return data
  }

  override fun saveNote(note: Note): LiveData<Note> {
    return MutableLiveData<Note>()
  }

  private fun propagateNotes() {
    mutableNotes.postValue(_notes.values.toList())
  }
}