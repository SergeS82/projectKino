package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.anudx.project_kino.databinding.ActivityFilmDetailsBinding
import ru.anudx.project_kino.model.FilmsData

class DetailsFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivityFilmDetailsBinding.inflate(layoutInflater)
        setContentView(b.root)
        val film = intent.extras?.get("film") as FilmsData
        b.toolbarLayout.title = film.title
        b.scrollingText.source.text = film.description
        b.image.setImageResource(film.image)
    }
}