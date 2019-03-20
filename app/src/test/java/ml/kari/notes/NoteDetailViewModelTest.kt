package ml.kari.notes

import androidx.lifecycle.*
import ml.kari.notes.model.*
import ml.kari.notes.repository.*
import ml.kari.notes.viewmodel.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.*
import org.powermock.api.mockito.PowerMockito.*
import org.powermock.core.classloader.annotations.*
import org.powermock.modules.junit4.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.TestRule
import org.junit.Rule



@RunWith(PowerMockRunner::class)
@PrepareForTest(NetworkNotesRepository::class)
class NoteDetailViewModelTest {

  @Rule
  var rule: TestRule = InstantTaskExecutorRule()

  @Test
  fun whenNoteEdited_warningDialogShown() {

    val repository = mockNotesRepository()
    val viewModel = NoteDetailViewModel(repository)
    viewModel.onNoteIdChanged(1)

    viewModel.onNoteChanged("newText")

    assertNull(viewModel.showConfirmDialog.value)
    viewModel.onBackClick()

    assertNotNull(viewModel.showConfirmDialog.value)
  }

  @Test
  fun whenDeleteClicked_warningDialogShown() {

    val repository = mockNotesRepository()
    val viewModel = NoteDetailViewModel(repository)
    viewModel.onNoteIdChanged(1)

    viewModel.onNoteChanged("newText")

    assertNull(viewModel.showConfirmDialog.value)
    viewModel.onDeleteClick()

    assertNotNull(viewModel.showConfirmDialog.value)
  }

  private fun mockNotesRepository(): NetworkNotesRepository {
    val repository = mock(NetworkNotesRepository::class.java)
    `when`(repository.getNote(1)).thenReturn(MutableLiveData<SavedNote>().apply{
      value = SavedNote(1, "title")
    })
    return repository
  }
}
