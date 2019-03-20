package ml.kari.notes.model

class SavedNote(
  var id: Int,
  title: String = ""
): Note(title)