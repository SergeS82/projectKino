package ru.anudx.project_kino

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MainActivityLifeCycleListener: LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start(){
        Log.d("debug_info","MainActivityLifeCycleListener.start")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop(){
        Log.d("debug_info","MainActivityLifeCycleListener.stop")
    }


}