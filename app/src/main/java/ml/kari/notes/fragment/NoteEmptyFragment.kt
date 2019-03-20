package ml.kari.notes.fragment

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.lifecycle.*
import androidx.navigation.fragment.*
import com.google.android.material.snackbar.*
import kotlinx.android.synthetic.main.fragment_note_detail.*
import ml.kari.notes.R
import ml.kari.notes.util.*
import ml.kari.notes.viewmodel.*
import org.koin.androidx.viewmodel.ext.android.*

class NoteEmptyFragment: BaseFragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_note_empty, container, false)
  }
}
