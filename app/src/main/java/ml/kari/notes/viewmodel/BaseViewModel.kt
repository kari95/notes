package ml.kari.notes.viewmodel

import androidx.lifecycle.*

abstract class BaseViewModel: ViewModel() {

  open fun onAttachedView() { }
}