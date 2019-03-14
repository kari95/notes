package ml.kari.notes.adapter

import android.view.*
import androidx.databinding.*
import androidx.recyclerview.widget.*
import ml.kari.notes.model.*
import ml.kari.notes.databinding.*

class MealAdapter(private val onClick: (Note) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  var notes: List<Note> = emptyList()
    set(value) {
      val result = DiffUtil.calculateDiff(DiffCallback(field, value))
      field = value

      result.dispatchUpdatesTo(this)
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return NoteViewHolder(ItemNoteBinding.inflate(inflater, parent, false))
  }

  override fun getItemCount() = notes.size

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val task = notes[position]
    when (holder) {
      is NoteViewHolder -> holder.bind(task, onClick)
    }
  }
}

class NoteViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

  fun bind(note: Note, listener: (Note) -> Unit) = with(binding.root) {
    if (binding is ItemNoteBinding)
      binding.note = note
    setOnClickListener { listener(note) }
  }
}

private class DiffCallback(val oldItems: List<Note>, val newItems: List<Note>)
  : DiffUtil.Callback() {

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val oldItem = oldItems[oldItemPosition]
    val newItem = newItems[newItemPosition]

    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val oldItem = oldItems[oldItemPosition]
    val newItem = newItems[newItemPosition]

    return oldItem == newItem
  }

  override fun getOldListSize() = oldItems.size

  override fun getNewListSize() = newItems.size
}
