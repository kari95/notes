package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import ml.kari.notes.R
import ml.kari.notes.databinding.*
import ml.kari.notes.viewmodel.*

class NoteDetailFragment: Fragment() {

  lateinit var binding: FragmentNotesListBinding

  lateinit var viewModel: NotesListViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes_list, container, false)
    setHasOptionsMenu(true)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(NotesListViewModel::class.java)
    binding.viewModel = viewModel

    viewModel.onScreenShowed()

    addListeners()
  }
  private fun addListeners() {

  }

}
