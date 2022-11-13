package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anudx.project_kino.adapters.CommonAdapter
import ru.anudx.project_kino.adapters.DelegateFilmsAdapter
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.decorations.RecyclerDecoration
import ru.anudx.project_kino.item_touch_helper.MainItemTouchHelper

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        initNavigation()
        val adapter = CommonAdapter(this)
        adapter.dataManager.init()
        with(b.filmsRecycler) {
            layoutManager = LinearLayoutManager(this@MainActivity);
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                RecyclerDecoration(
                    this@MainActivity,
                    resources.getDimensionPixelSize(R.dimen.side_padding),
                    resources.getDimensionPixelSize(R.dimen.top_padding)
                )
            )
            this.adapter = adapter
            val touchHelper = ItemTouchHelper(MainItemTouchHelper( adapter))
            touchHelper.attachToRecyclerView(this)
        }
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