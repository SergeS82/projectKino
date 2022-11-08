package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anudx.project_kino.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        initNavigation()
        setUpFilms()
        b.recyclerView.layoutManager = LinearLayoutManager(this)
        b.recyclerView.setHasFixedSize(true)
        b.recyclerView.itemAnimator = RecyclerViewItemAnimator(this) //DefaultItemAnimator()
        adapter = MainAdapter(this, PopulateData(this).dataToBeParsed())
        b.recyclerView.adapter = adapter
        val sidePadding = resources.getDimensionPixelSize(R.dimen.sidPadding)
        val topPadding = resources.getDimensionPixelSize(R.dimen.sidPadding)
        b.recyclerView.addItemDecoration(RecyclerViewDecoration(this, sidePadding, topPadding)) // может быть несколько декороторов
        b.recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.recycle_lalyout_animation)
        b.recyclerView.scheduleLayoutAnimation()
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
                    //Toast.makeText(this, R.string.btn_favorites, Toast.LENGTH_SHORT).show()
                    PopulateData(this).addData(adapter)
                    true
                }
                R.id.menu_later -> {
                    //Toast.makeText(this, R.string.btn_later, Toast.LENGTH_SHORT).show()
                    PopulateData(this).removeLast(adapter)
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
    private fun setUpFilms(){

    }
}

