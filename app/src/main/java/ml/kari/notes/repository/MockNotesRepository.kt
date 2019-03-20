package ml.kari.notes.repository

import androidx.lifecycle.*
import kotlinx.coroutines.*
import ml.kari.notes.model.*
import ml.kari.notes.network.*
import retrofit2.*
import timber.log.*

class MockNotesRepository: NotesRepository {

  private val _notes: MutableList<SavedNote> = mutableListOf()
  private val mutableNotes: MutableLiveData<List<SavedNote>> = MutableLiveData()

  override val notes: LiveData<List<SavedNote>> = mutableNotes

  override fun updateNotes() {
    _notes.apply {
      add(SavedNote(size, "Buy some coffee"))
      add(SavedNote(size, "Don't forget pay internet"))
      add(SavedNote(size, "Muffins receipt:\n" +
        "INGREDIENTS\n" +
        "2 cups white flour\n" +
        "1 tablespoon baking powder\n" +
        "1/2 teaspoon salt\n" +
        "2 tablespoons sugar\n" +
        "1 egg, slightly beaten\n" +
        "1 cup milk\n" +
        "1/4 cup melted butter"))
      add(SavedNote(size, "Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. "))

    }
    propagateNotes()
  }

  override fun getNote(id: Int): LiveData<SavedNote> {
    val data = MutableLiveData<SavedNote>()
    if (id > 0 &&  id < _notes.size) {
      data.value = _notes[id]
    } else {
      data.value = SavedNote(id)
    }
    return data
  }

  override fun saveNote(note: Note): LiveData<SavedNote> {
    val data = MutableLiveData<SavedNote>()
    if (note is SavedNote) {
      _notes[note.id] = note
      data.postValue(_notes[note.id])
    } else {
      val newNote = SavedNote(
        _notes.size,
        note.title
      )
      _notes.add(newNote)
      data.postValue(newNote)
    }
    return data
  }

  override fun deleteNote(id: Int): LiveData<SavedNote> {
    return MutableLiveData<SavedNote>()
  }

  private fun propagateNotes() {
    mutableNotes.postValue(_notes)
  }
}