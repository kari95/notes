package ml.kari.notes.activity

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.navigation.fragment.*
import ml.kari.notes.R

class MainActivity: AppCompatActivity() {

  lateinit var navHost: NavHostFragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    navHost = supportFragmentManager
      .findFragmentById(R.id.nav_host) as NavHostFragment
  }

  override fun onSupportNavigateUp(): Boolean {
    return navHost.navController.navigateUp() || super
      .onSupportNavigateUp()
  }
}
