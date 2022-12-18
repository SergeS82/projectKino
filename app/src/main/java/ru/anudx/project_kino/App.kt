package ru.anudx.project_kino

import android.app.Application
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.lifecycle.LifecycleObserver
import ru.anudx.project_kino.model.FilmsData
import ru.anudx.project_kino.model.InterfaceData

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
        lateinit var mainContext: MainActivity
    }
}