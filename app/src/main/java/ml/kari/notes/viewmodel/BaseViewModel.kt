package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.notes.R
import ml.kari.notes.livedata.*

abstract class BaseViewModel: ViewModel() {

  val errorMessage: LiveData<Event<Int>> = MutableLiveData()

  open fun onAttachedView() { }

  protected fun showConnectionError() {
    (errorMessage as MutableLiveData).postValue(Event(R.string.connection_error))
  }
}