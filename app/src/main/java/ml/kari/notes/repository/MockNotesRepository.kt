package ml.kari.notes.repository

import androidx.lifecycle.*
import kotlinx.coroutines.*
import ml.kari.notes.model.*
import ml.kari.notes.network.*
import retrofit2.*
import timber.log.*

class MockNotesRepository: NotesRepository {

  private val _notes: MutableList<Note> = mutableListOf()
  private val mutableNotes: MutableLiveData<List<Note>> = MutableLiveData()

  override val notes: LiveData<List<Note>> = mutableNotes

  override fun updateNotes() {
    _notes.apply {
      add(Note(size, "Buy some coffee"))
      add(Note(size, "Don't forget pay internet"))
      add(Note(size, "Muffins receipt:\n" +
        "INGREDIENTS\n" +
        "2 cups white flour\n" +
        "1 tablespoon baking powder\n" +
        "1/2 teaspoon salt\n" +
        "2 tablespoons sugar\n" +
        "1 egg, slightly beaten\n" +
        "1 cup milk\n" +
        "1/4 cup melted butter"))
      add(Note(size, "Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. Very long note. "))

    }
    propagateNotes()
  }

  override fun getNote(id: Int): LiveData<Note> {
    val data = MutableLiveData<Note>()
    data.value = _notes[id]
    return data
  }

  override fun saveNote(note: Note) {
  }

  private fun propagateNotes() {
    mutableNotes.postValue(_notes)
  }
}