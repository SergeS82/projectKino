package ru.anudx.project_kino

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.anudx.project_kino.databinding.ScrollBasedActivityBinding

class ScrollBasedActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ScrollBasedActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
    }
}