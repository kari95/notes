package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_notes_list.*
import ml.kari.notes.*
import ml.kari.notes.adapter.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class NotesListFragment: BaseFragment() {

  private val viewModel: NotesListViewModel by viewModel()
  private val notesAdapter = NotesAdapter(viewModel::onNoteClick)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_notes_list, container, false)
  }

  override fun setupView() {
    notes_list.adapter = notesAdapter
  }

  override fun addListeners() {
    viewModel.notes.observe(this, Observer { notes ->
      notesAdapter.notes = notes
    })
  }

  override fun onStart() {
    super.onStart()

    viewModel.onScreenShowed()
  }

}
