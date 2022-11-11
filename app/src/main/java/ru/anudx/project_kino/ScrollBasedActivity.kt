package ru.anudx.project_kino

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ru.anudx.project_kino.databinding.ScrollBasedActivityBinding

class ScrollBasedActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ScrollBasedActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
        val snackbar = Snackbar.make(b.root, "Snackbar!", Snackbar.LENGTH_SHORT)
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.teal_200))
        snackbar.setAction("Action"){
            Toast.makeText(this, "Toast!", Toast.LENGTH_SHORT).show()
        }
        b.fab2.setOnClickListener {
            when (snackbar.isShown){
                true -> snackbar.dismiss()
                else -> snackbar.show()
            }
        }

    }
}