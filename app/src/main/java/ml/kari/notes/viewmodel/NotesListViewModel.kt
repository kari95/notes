package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.notes.repository.*

class NotesListViewModel(
  private val notesRepository: NotesRepository
): BaseViewModel() {

  val message: MutableLiveData<String> = MutableLiveData()

  fun onScreenShowed() {

  }
}