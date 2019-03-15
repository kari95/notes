package ml.kari.notes.network

import kotlinx.coroutines.*
import ml.kari.notes.model.*
import retrofit2.http.*


interface NotesRequestService {

  @GET("notes")
  fun getNotes(): Deferred<List<Note>>

  @GET("notes/{id}")
  fun getNote(@Path("id") id: Int): Deferred<Note>

  @POST("notes")
  fun addNote(@Body note: Note): Deferred<Note>

  @POST("notes/{id}")
  fun updateNote(@Path("id") id: Int, @Body note: Note): Deferred<Note>
}