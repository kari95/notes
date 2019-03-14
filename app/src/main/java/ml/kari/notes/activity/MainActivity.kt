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

    navHost = supportFragmentManager
      .findFragmentById(R.id.nav_host) as NavHostFragment
    
  }

  /*
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
      return NavigationUI.onNavDestinationSelected(item, navHost.navController) || super.onOptionsItemSelected(item)
  }
  */

  override fun onSupportNavigateUp(): Boolean {
    return navHost.navController.navigateUp() || super.onSupportNavigateUp()
    //return NavigationUI.navigateUp(drawer, navHost.navController) || super.onSupportNavigateUp()
  }
}
