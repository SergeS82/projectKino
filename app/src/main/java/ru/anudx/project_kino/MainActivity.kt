package ru.anudx.project_kino

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.databinding.BarLayoutTestBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bam = ActivityMainBinding.inflate(layoutInflater)
        val bblt = BarLayoutTestBinding.inflate(layoutInflater)
        setContentView(bblt.root)
        bblt.topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Когда-нибудь сдесь будет навигация ...", Toast.LENGTH_SHORT).show() }
        bblt.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.fav -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.search -> {
                    Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.more ->{
                    Toast.makeText(this, "Ещё", Toast.LENGTH_SHORT).show()
                    true
                }
                else ->false
            }
        }
    }
}