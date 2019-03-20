package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.*
import kotlinx.android.synthetic.main.fragment_note_detail.*
import kotlinx.android.synthetic.main.fragment_notes_list.*
import ml.kari.notes.R
import ml.kari.notes.adapter.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class NotesListFragment: BaseFragment() {

  private val viewModel: NotesListViewModel by sharedViewModel()
  private var notesAdapter: NotesAdapter? = null

  private var snackbar: Snackbar? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_notes_list, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.onAttachedView()
  }

  override fun setupView() {

    toolbar_background.layoutParams.height = getStatusBarHeight()
    toolbar_background.requestLayout()

    toolbar.setPadding(0, getStatusBarHeight(), 0, 0)
    toolbar.layoutParams.height += getStatusBarHeight()
    toolbar.title = getString(R.string.app_name)

    notesAdapter = NotesAdapter(viewModel::onNoteClick)
    notes_list.adapter = notesAdapter
    notes_list.layoutManager = LinearLayoutManager(context)
    notes_list.isNestedScrollingEnabled = true
  }

  override fun addListeners() {

    swipe_refresh.setOnRefreshListener(viewModel::onRefresh)

    add_button.setOnClickListener {
      viewModel.onAddClick()
    }

    viewModel.notes.observe(this, Observer { notes ->
      if (notes != null) {
        notesAdapter?.notes = notes
      }
      swipe_refresh.isRefreshing = false
    })

    viewModel.errorMessage.observe(this, Observer { message ->
      if (message != null && snackbar?.isShown != true) {
        snackbar = Snackbar.make(notes_list, message, Snackbar.LENGTH_LONG)
        snackbar?.show()
      }
    })
  }
}
