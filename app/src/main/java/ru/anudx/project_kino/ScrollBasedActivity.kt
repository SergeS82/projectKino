package ru.anudx.project_kino

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.anudx.project_kino.databinding.ScrollBasedActivityBinding

class ScrollBasedActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ScrollBasedActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
        val snackbar = Snackbar.make(b.root, "Snackbar!", Snackbar.LENGTH_SHORT)
        b.fab2.setOnClickListener {
            when (snackbar.isShown){
                true -> snackbar.dismiss()
                else -> snackbar.show()
            }
        }

    }
}