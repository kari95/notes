package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import kotlinx.android.synthetic.main.fragment_notes_list.*
import ml.kari.notes.R
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class NoteDetailFragment: BaseFragment(), MenuItem.OnMenuItemClickListener {

  private val viewModel: NoteDetailViewModel by viewModel()
  private val args: NoteDetailFragmentArgs by navArgs()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_note_detail, container, false)
  }

  override fun setupView() {

    setHasOptionsMenu(true)

    toolbar.setPadding(0, getStatusBarHeight(), 0, 0)
    toolbar.layoutParams.height += getStatusBarHeight()
    val supportActivity = activity as AppCompatActivity
    supportActivity.setSupportActionBar(toolbar)
    supportActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
  }

  override fun addListeners() {

    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      notes_list.addOnScrollListener(object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
          super.onScrolled(recyclerView, dx, dy)

          if (!recyclerView.canScrollVertically(-1)) {
            // we have reached the top of the list
            toolbar?.elevation = 0f
          } else {
            // we are not at the top yet
            toolbar?.elevation = 20f
          }
        }
      })
    }*/
  }

  override fun onStart() {
    super.onStart()

    viewModel.onScreenShowed(args.noteId)
  }

  override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.note_detail_menu, menu)
    menu.getItem(0).setOnMenuItemClickListener(this)
  }

  override fun onMenuItemClick(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.action_delete -> {
        viewModel.onDeleteClick()
        return true
      }
    }
    return false
  }
}
