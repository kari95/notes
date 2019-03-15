package ml.kari.notes.activity

import android.os.*
import androidx.appcompat.app.*
import androidx.navigation.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import ml.kari.notes.R

class MainActivity: AppCompatActivity() {

  lateinit var navHost: NavHostFragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    navHost = supportFragmentManager
      .findFragmentById(R.id.nav_host) as NavHostFragment
  }

  override fun onSupportNavigateUp(): Boolean {
    return navHost.navController.navigateUp() || super.onSupportNavigateUp()
  }
}
