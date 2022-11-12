package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.anudx.project_kino.databinding.ActivityFilmDetailsBinding

class DetailsActivityFilm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivityFilmDetailsBinding.inflate(layoutInflater)
        setContentView(b.root)
    }
}