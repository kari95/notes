package ml.kari.notes.fragment

import android.os.*
import androidx.fragment.app.*
import ml.kari.notes.*

abstract class BaseFragment: Fragment() {

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    setupView()
    addListeners()
  }

  protected open fun setupView() {  }

  protected open fun addListeners() { }

  protected fun getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
      result = resources.getDimensionPixelSize(resourceId)
    }
    return result
  }

  protected fun isTablet(): Boolean = resources.getBoolean(R.bool.is_tablet)
}
