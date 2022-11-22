package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.anudx.project_kino.databinding.ActivityReturnBinding

class ReturnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivityReturnBinding.inflate(layoutInflater)
        setContentView(b.root)
        setSupportActionBar(b.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        b.toolBar.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}