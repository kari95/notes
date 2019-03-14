package ml.kari.notes

import android.annotation.*
import android.app.*
import android.util.*
import org.koin.android.ext.android.*
import timber.log.*

class App: Application() {

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

    startKoin(this, listOf())

    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }

}