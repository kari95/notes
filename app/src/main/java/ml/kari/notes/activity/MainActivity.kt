package ml.kari.notes.activity

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.*
import com.google.android.material.snackbar.*
import kotlinx.android.synthetic.main.fragment_note_detail.*
import ml.kari.notes.R
import ml.kari.notes.fragment.*
import ml.kari.notes.model.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class MainActivity: AppCompatActivity() {

  private val listViewModel: NotesListViewModel by viewModel()

  private lateinit var listNavHost: NavHostFragment
  private var detailNavHost: NavHostFragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    listNavHost = supportFragmentManager
      .findFragmentById(R.id.nav_host) as NavHostFragment

    if (isTablet()) {
      detailNavHost = supportFragmentManager
        .findFragmentById(R.id.nav_host_detail) as NavHostFragment
    }

    setupView()
    addListeners()
  }

  private fun setupView() {
  }

  private fun addListeners() {
    listViewModel.openNote.observe(this, Observer { note ->
      if (isTablet()) {
        val action = NoteDetailFragmentDirections.actionOpenDetail()
        if (note is SavedNote) {
          action.noteId = note.id
        }
        detailNavHost?.navController?.navigate(action)
      } else {
        val action = NotesListFragmentDirections.actionOpenDetail()
        if (note is SavedNote) {
          action.noteId = note.id
        }
        listNavHost.navController.navigate(action)
      }
    })
  }

  override fun onSupportNavigateUp(): Boolean {
    return listNavHost.navController.navigateUp() || super
      .onSupportNavigateUp()
  }

  private fun isTablet(): Boolean = resources.getBoolean(R.bool.is_tablet)
}
