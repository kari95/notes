package ml.kari.notes.activity

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.*
import ml.kari.notes.R
import ml.kari.notes.fragment.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class MainActivity: AppCompatActivity() {

  private val listViewModel: NotesListViewModel by viewModel()

  lateinit var navHost: NavHostFragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    navHost = supportFragmentManager
      .findFragmentById(R.id.nav_host) as NavHostFragment

    setupView()
    addListeners()
  }

  private fun setupView() {

  }

  private fun addListeners() {
    listViewModel.openNote.observe(this, Observer { note ->
      val action = NotesListFragmentDirections.actionOpenDetail(note.id)
      navHost.navController.navigate(action)
    })
  }

  override fun onSupportNavigateUp(): Boolean {
    return navHost.navController.navigateUp() || super
      .onSupportNavigateUp()
  }
}
