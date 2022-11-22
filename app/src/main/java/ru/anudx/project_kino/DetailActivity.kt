package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.anudx.project_kino.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(b.root)
        val bundle = intent.extras?.getBundle("bundle")
        if (bundle != null) {
            b.image.setImageResource(bundle.getInt("image"))
            b.toolbarLayout.title = bundle.getString("title")
        }
    }
}