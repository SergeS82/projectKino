package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.databinding.BarLayoutTestBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val bam = ActivityMainBinding.inflate(layoutInflater)
        val bam = BarLayoutTestBinding.inflate(layoutInflater)
        setContentView(bam.root)
//        bam.btnMenu.setOnClickListener { Toast.makeText(this, R.string.btn_menu, Toast.LENGTH_SHORT).show() }
//        bam.btnFavorites.setOnClickListener { Toast.makeText(this, R.string.btn_favorites, Toast.LENGTH_SHORT).show() }
//        bam.btnLate.setOnClickListener { Toast.makeText(this, R.string.btn_later, Toast.LENGTH_SHORT).show() }
//        bam.btnSelections.setOnClickListener { Toast.makeText(this, R.string.btn_selections, Toast.LENGTH_SHORT).show() }
//        bam.btnSettings.setOnClickListener { Toast.makeText(this, R.string.btn_settings, Toast.LENGTH_SHORT).show() }
    }
}