package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import ml.kari.notes.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class NotesListFragment: Fragment() {

  private val viewModel: NotesListViewModel by viewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_notes_list, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.onScreenShowed()

    addListeners()
  }
  private fun addListeners() {

  }

}
