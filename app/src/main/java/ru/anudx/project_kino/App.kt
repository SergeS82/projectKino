package ru.anudx.project_kino

import android.app.Application
import androidx.lifecycle.LifecycleObserver

class App: Application() {
    val objectInAppClass = "Object"
    override fun onCreate() {
        super.onCreate()
        instance = this
        lifeCycleListener = MainActivityLifeCycleListener()
    }

    companion object {
        lateinit var instance: App
            private set
        lateinit var lifeCycleListener: LifecycleObserver
            private set
        lateinit var mainContext: MainFragmentActivity
    }
}