package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import ml.kari.notes.R

abstract class BaseViewModel: ViewModel() {

  val errorMessage: LiveData<Int> = MutableLiveData()

  open fun onAttachedView() { }

  protected fun showConnectionError() {
    (errorMessage as MutableLiveData).postValue(R.string.connection_error)
  }
}