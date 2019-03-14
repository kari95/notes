package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.*
import ml.kari.notes.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class NotesListFragment: Fragment() {

  lateinit var binding: ViewDataBinding

  val viewModel: NotesListViewModel by viewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes_list, container, false)
    setHasOptionsMenu(true)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    //viewModel = ViewModelProviders.of(this).get(NotesListViewModel::class.java)
    //binding.viewModel = viewModel

    viewModel.onScreenShowed()

    addListeners()
  }
  private fun addListeners() {

  }

}
