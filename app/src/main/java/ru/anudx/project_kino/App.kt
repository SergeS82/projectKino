package ru.anudx.project_kino

import android.app.Application

class App: Application() {
    val objectInAppClass = "Object"
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}