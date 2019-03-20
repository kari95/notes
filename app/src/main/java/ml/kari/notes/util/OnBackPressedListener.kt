package ml.kari.notes.util

interface OnBackPressedListener {

  /**
   * return true if was handled
   */
  fun onBackPressed(): Boolean
}