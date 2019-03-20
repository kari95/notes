package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.lifecycle.*
import androidx.navigation.fragment.*
import com.google.android.material.snackbar.*
import kotlinx.android.synthetic.main.fragment_note_detail.*
import ml.kari.notes.R
import ml.kari.notes.util.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class NoteDetailFragment: BaseFragment(), MenuItem.OnMenuItemClickListener {

  private val viewModel: NoteDetailViewModel by sharedViewModel()
  private val args: NoteDetailFragmentArgs by navArgs()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_note_detail, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.onAttachedView()
    viewModel.onNoteIdChanged(args.noteId)
  }

  override fun setupView() {

    setHasOptionsMenu(true)

    detail_toolbar.setPadding(0, getStatusBarHeight(), 0, 0)
    detail_toolbar.layoutParams.height += getStatusBarHeight()
    val supportActivity = activity as AppCompatActivity
    supportActivity.setSupportActionBar(detail_toolbar)
    supportActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
    if (!isTablet()) {
      supportActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
  }

  override fun addListeners() {

    note_text.afterTextChanged {  text ->
      viewModel.onNoteChanged(text)
    }

    viewModel.note.observe(this, Observer { note ->
      note_text.setText(note?.title)
    })

    viewModel.errorMessage.observe(this, Observer { message ->
      if (message != null) {
        Snackbar.make(note_text, message, Snackbar.LENGTH_LONG)
          .show()
      }
    })

    viewModel.closeNote.observe(this, Observer {
      NavHostFragment.findNavController(this).navigateUp()
    })
    viewModel.loading.observe(this, Observer { visible ->
      loading_overlay.visibility = if (visible == true) View.VISIBLE else View.GONE
    })

    viewModel.showConfirmDialog.observe(this, Observer { callback ->
      context?.let { context ->
        AlertDialog.Builder(context, R.style.AlertDialog)
          .setTitle(R.string.warning)
          .setMessage(R.string.really)
          .setPositiveButton(android.R.string.yes) { _, _ -> callback(true) }
          .setNegativeButton(android.R.string.no) { _, _ -> callback(false) }
          .show()
      }
    })
  }

  override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.note_detail_menu, menu)
    menu.getItem(0).setOnMenuItemClickListener(this)
    menu.getItem(1).setOnMenuItemClickListener(this)
  }

  override fun onMenuItemClick(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.action_delete -> {
        viewModel.onDeleteClick()
        return true
      }
      R.id.action_save -> {
        viewModel.onSaveClick()
        return true
      }
    }
    return false
  }
}
