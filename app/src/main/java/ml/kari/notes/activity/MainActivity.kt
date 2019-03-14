package ml.kari.notes.activity

import android.os.*
import androidx.navigation.fragment.*
import ml.kari.core.activity.*
import ml.kari.notes.R
import ml.kari.notes.databinding.*

class MainActivity: BaseActivity<ActivityMainBinding>() {

  lateinit var navHost: NavHostFragment


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    bindView(R.layout.activity_main)
    setSupportActionBar(binding.toolbar)

    navHost = supportFragmentManager
      .findFragmentById(R.id.nav_host) as NavHostFragment
  }

  override fun onSupportNavigateUp(): Boolean {
    return navHost.navController.navigateUp() || super.onSupportNavigateUp()
  }
}
