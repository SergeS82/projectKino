package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.anudx.project_kino.databinding.ActivityFilmDetailsBinding
import ru.anudx.project_kino.databinding.ContentScrollingBinding
import ru.anudx.project_kino.model.FilmsData

class DetailsActivityFilm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivityFilmDetailsBinding.inflate(layoutInflater)
        setContentView(b.root)
        val film = intent.extras?.get("film") as FilmsData
        b.toolbar.title = film.title
        b.description.description.text = film.description
        b.image.setImageResource(film.image)
    }
}