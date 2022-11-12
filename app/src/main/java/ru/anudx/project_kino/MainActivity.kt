package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.Animation
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.anudx.project_kino.adapters.FilmsAdapter
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.decorations.RecyclerDecoration

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        initNavigation()
        b.filmsRecycler.layoutManager = LinearLayoutManager(this);
        b.filmsRecycler.itemAnimator = DefaultItemAnimator()
        b.filmsRecycler.addItemDecoration(RecyclerDecoration(this, resources.getDimensionPixelSize(R.dimen.side_padding), resources.getDimensionPixelSize(R.dimen.top_padding)))
        val adapter = FilmsAdapter(this)
        b.filmsRecycler.adapter = adapter
        adapter.dataManager.init()

    }

    private fun initNavigation() {
        b.toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.Settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        b.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_favorites -> {
                    Toast.makeText(this, R.string.btn_favorites, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_later -> {
                    Toast.makeText(this, R.string.btn_later, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_library -> {
                    Toast.makeText(this, R.string.btn_library, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}