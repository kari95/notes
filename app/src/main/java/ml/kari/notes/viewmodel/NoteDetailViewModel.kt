package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.core.viewmodel.*

class NoteDetailViewModel: BaseViewModel() {

  val message: MutableLiveData<String> = MutableLiveData()

  fun onScreenShowed() {

  }
}