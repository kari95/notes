package ml.kari.notes.network

import kotlinx.coroutines.*
import ml.kari.notes.model.*
import retrofit2.*
import retrofit2.http.*


interface NotesRequestService {

  @GET("notes")
  fun getNotes(): Deferred<List<SavedNote>>

  @GET("notes/{id}")
  fun getNote(@Path("id") id: Int): Deferred<SavedNote>

  @POST("notes")
  fun addNote(@Body note: Note): Deferred<SavedNote>

  @PUT("notes/{id}")
  fun updateNote(@Path("id") id: Int, @Body note: Note): Deferred<SavedNote>

  @DELETE("notes/{id}")
  fun deleteNote(@Path("id") id: Int): Deferred<Response<Unit>>
}