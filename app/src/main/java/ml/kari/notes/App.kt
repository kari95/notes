package ml.kari.notes

import android.annotation.*
import android.app.*
import android.util.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.*
import ml.kari.notes.network.*
import ml.kari.notes.repository.*
import ml.kari.notes.viewmodel.*
import org.koin.android.ext.android.*
import org.koin.androidx.viewmodel.ext.koin.*
import org.koin.dsl.module.*
import retrofit2.*
import retrofit2.converter.moshi.*
import timber.log.*

class App: Application() {

  companion object {
    val applicationModule : Module = module {
      viewModel { NoteDetailViewModel() }
      viewModel { NotesListViewModel(get()) }
      single { MockNotesRepository() as NotesRepository }
    }

    val networkModule : Module = module {
      single( definition = { createWebService<NotesRequestService>(UrlConst.API_ENDPOINT) })
    }

    private inline fun <reified T> createWebService(url: String): T {
      val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
      return retrofit.create(T::class.java)
    }
  }

  /**
   * [Timber.Tree] class used for logging in debug mode.
   */
  private class DebugTree: Timber.DebugTree() {
    override fun isLoggable(tag: String?, priority: Int): Boolean {
      return priority >= Log.VERBOSE
    }

    override fun createStackElementTag(element: StackTraceElement): String? {
      return super.createStackElementTag(element) + ":" + element.lineNumber
    }

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
      val fullTag = "app $tag"
      when (priority) {
        Log.VERBOSE -> Log.v(fullTag, message, throwable)
        Log.DEBUG -> Log.d(fullTag, message, throwable)
        Log.INFO -> Log.i(fullTag, message, throwable)
        Log.WARN -> Log.w(fullTag, message, throwable)
        Log.ERROR -> Log.e(fullTag, message, throwable)
      }
    }
  }

  override fun onCreate() {
    super.onCreate()

    startKoin(this, listOf(applicationModule, networkModule))

    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }
}