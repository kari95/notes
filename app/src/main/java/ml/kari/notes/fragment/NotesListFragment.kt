package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.fragment_notes_list.*
import ml.kari.notes.*
import ml.kari.notes.adapter.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class NotesListFragment: BaseFragment() {

  private val viewModel: NotesListViewModel by viewModel()
  private var notesAdapter: NotesAdapter? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_notes_list, container, false)
  }

  override fun setupView() {
    notesAdapter = NotesAdapter(viewModel::onNoteClick)
    notes_list.adapter = notesAdapter
    notes_list.layoutManager = LinearLayoutManager(context)
  }

  override fun addListeners() {
    swipe_refresh.setOnRefreshListener(viewModel::onRefresh)

    viewModel.notes.observe(this, Observer { notes ->
      notesAdapter?.notes = notes
      swipe_refresh.isRefreshing = false
    })
  }

  override fun onStart() {
    super.onStart()

    viewModel.onScreenShowed()
  }

}
