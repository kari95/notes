package ml.kari.notes.activity

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.*
import ml.kari.notes.R
import ml.kari.notes.fragment.*
import ml.kari.notes.model.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*
import ml.kari.notes.util.OnBackPressedListener



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
    listViewModel.openNote.observe(this, Observer { event ->
      event.getContentIfNotHandled()?.let { note ->
        if (isTablet()) {
          // Navigate inside detail nav host.
          val action = NoteDetailFragmentDirections.actionOpenDetail()
          if (note is SavedNote) {
            action.noteId = note.id
          }
          detailNavHost?.navController?.navigate(action)
        } else {
          // Navigate inside list nav host.
          val action = NotesListFragmentDirections.actionOpenDetail()
          if (note is SavedNote) {
            action.noteId = note.id
          }
          listNavHost.navController.navigate(action)
        }
      }
    })
  }

  override fun onBackPressed() {
    detailNavHost?.let { hostFragment ->
      val currentFragment = hostFragment.childFragmentManager.fragments[0]
      if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()) {
        return
      }
    }
    val currentFragment = listNavHost.childFragmentManager.fragments[0]
    if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()) {
      return
    }
    super.onBackPressed()
  }

  private fun isTablet(): Boolean = resources.getBoolean(R.bool.is_tablet)
}
