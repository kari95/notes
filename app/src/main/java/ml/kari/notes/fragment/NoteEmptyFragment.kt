package ml.kari.notes.fragment

import android.os.*
import android.view.*
import ml.kari.notes.*

class NoteEmptyFragment: BaseFragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_note_empty, container, false)
  }
}
