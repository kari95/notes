package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.fragment.app.*
import ml.kari.notes.R
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class NoteDetailFragment: BaseFragment() {

  private val viewModel: NotesListViewModel by viewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_note_detail, container, false)
  }

  override fun setupView() {
  }

  override fun addListeners() {

  }

  override fun onStart() {
    super.onStart()

    viewModel.onScreenShowed()
  }

}
