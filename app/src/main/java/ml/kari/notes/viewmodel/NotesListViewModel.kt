package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.core.viewmodel.*

class NotesListViewModel: BaseViewModel() {

  val message: MutableLiveData<String> = MutableLiveData()

  fun onScreenShowed() {

  }
}