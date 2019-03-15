package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import kotlinx.android.synthetic.main.fragment_notes_list.*
import ml.kari.notes.*
import ml.kari.notes.adapter.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

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
}
