package ml.kari.notes.network

import androidx.lifecycle.*
import ml.kari.notes.model.*
import retrofit2.*
import retrofit2.http.*


interface NotesRequestService {

  @GET("notes/")
  fun getNotes(): LiveData<List<Note>>

  @GET("notes/{id}")
  fun getNote(@Path("id") id: Int): LiveData<Note>

  @POST("notes/")
  fun addNote(@Body note: Note): LiveData<Note>

  @POST("notes/{id}")
  fun updateNote(@Path("id") id: Int, @Body note: Note): LiveData<Note>
}