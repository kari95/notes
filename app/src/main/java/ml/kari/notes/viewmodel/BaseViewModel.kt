package ml.kari.notes.viewmodel

import androidx.lifecycle.*
import io.reactivex.disposables.*

abstract class BaseViewModel: ViewModel() {

  protected var disposables: CompositeDisposable = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()

    disposables.dispose()
  }

}