package ml.kari.notes.viewmodel

import androidx.lifecycle.*

class NoteDetailViewModel: BaseViewModel() {

  val message: MutableLiveData<String> = MutableLiveData()

  fun onScreenShowed() {

  }
}