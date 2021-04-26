package cl.gersard.covidexercise

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CovidExerciseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) debugConfig()
    }

    private fun debugConfig() {
        Timber.plant(Timber.DebugTree())
    }

}